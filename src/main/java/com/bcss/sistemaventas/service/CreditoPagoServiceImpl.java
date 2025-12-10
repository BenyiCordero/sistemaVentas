package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.dto.response.CreditoPagoBasicResponse;
import com.bcss.sistemaventas.dto.response.CreditoPagoResponse;
import com.bcss.sistemaventas.dto.response.CreditoPagoListResponse;
import com.bcss.sistemaventas.dto.response.CreditoPagoDetailResponse;
import com.bcss.sistemaventas.dto.request.CreditoPagoRequest;
import com.bcss.sistemaventas.domain.CreditoPago;
import com.bcss.sistemaventas.domain.Credito;
import com.bcss.sistemaventas.domain.Pago;
import com.bcss.sistemaventas.repository.CreditoPagoRepository;
import com.bcss.sistemaventas.repository.CreditoRepository;
import com.bcss.sistemaventas.repository.PagoRepository;
import com.bcss.sistemaventas.mappers.CreditoPagoMapper;
import com.bcss.sistemaventas.exception.NotFoundException;
import com.bcss.sistemaventas.exception.RepeatedException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CreditoPagoServiceImpl implements CreditoPagoService {

    private final CreditoPagoRepository creditoPagoRepository;
    private final CreditoRepository creditoRepository;
    private final PagoRepository pagoRepository;
    private final CreditoPagoMapper creditoPagoMapper;

    @Override
    public CreditoPagoResponse crearCreditoPago(CreditoPagoRequest request) {
        Credito credito = creditoRepository.findById(request.idCredito())
                .orElseThrow(() -> new NotFoundException("Crédito no encontrado con ID: " + request.idCredito()));

        Pago pago = null;
        if (request.idPago() != null) {
            pago = pagoRepository.findById(request.idPago())
                    .orElseThrow(() -> new NotFoundException("Pago no encontrado con ID: " + request.idPago()));
            
            if (creditoPagoRepository.existsByCreditoIdCreditoAndPagoIdPago(request.idCredito(), request.idPago())) {
                throw new RepeatedException("Ya existe una relación entre el crédito y el pago especificados");
            }
        }

        CreditoPago creditoPago = CreditoPago.builder()
                .credito(credito)
                .pago(pago)
                .monto(request.monto())
                .build();

        CreditoPago savedCreditoPago = creditoPagoRepository.save(creditoPago);
        return creditoPagoMapper.toResponse(savedCreditoPago);
    }

    @Override
    @Transactional(readOnly = true)
    public CreditoPagoResponse obtenerCreditoPagoPorId(Integer id) {
        CreditoPago creditoPago = creditoPagoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("CreditoPago no encontrado con ID: " + id));
        return creditoPagoMapper.toResponse(creditoPago);
    }

    @Override
    @Transactional(readOnly = true)
    public CreditoPagoDetailResponse obtenerDetalleCreditoPagoPorId(Integer id) {
        CreditoPago creditoPago = creditoPagoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("CreditoPago no encontrado con ID: " + id));
        return creditoPagoMapper.toDetailResponse(creditoPago);
    }

    @Override
    @Transactional(readOnly = true)
    public CreditoPagoListResponse listarCreditoPagos() {
        Pageable pageable = PageRequest.of(0, 20);
        Page<CreditoPago> page = creditoPagoRepository.findAll(pageable);
        
        List<CreditoPagoBasicResponse> creditoPagos = page.getContent().stream()
                .map(creditoPagoMapper::toBasicResponse)
                .collect(Collectors.toList());

        return new CreditoPagoListResponse(
                creditoPagos,
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber(),
                page.hasNext(),
                page.hasPrevious()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public CreditoPagoListResponse listarCreditoPagosPorCredito(Integer idCredito) {
        Credito credito = creditoRepository.findById(idCredito)
                .orElseThrow(() -> new NotFoundException("Crédito no encontrado con ID: " + idCredito));

        List<CreditoPago> creditoPagos = creditoPagoRepository.findByCreditoIdCredito(idCredito);
        
        List<CreditoPagoBasicResponse> responses = creditoPagos.stream()
                .map(creditoPagoMapper::toBasicResponse)
                .collect(Collectors.toList());

        return new CreditoPagoListResponse(
                responses,
                (long) responses.size(),
                1,
                0,
                false,
                false
        );
    }

    @Override
    @Transactional(readOnly = true)
    public CreditoPagoListResponse listarCreditoPagosPorPago(Integer idPago) {
        Pago pago = pagoRepository.findById(idPago)
                .orElseThrow(() -> new NotFoundException("Pago no encontrado con ID: " + idPago));

        List<CreditoPago> creditoPagos = creditoPagoRepository.findByPagoIdPago(idPago);
        
        List<CreditoPagoBasicResponse> responses = creditoPagos.stream()
                .map(creditoPagoMapper::toBasicResponse)
                .collect(Collectors.toList());

        return new CreditoPagoListResponse(
                responses,
                (long) responses.size(),
                1,
                0,
                false,
                false
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<CreditoPagoResponse> listarCreditoPagosPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        List<CreditoPago> creditoPagos = creditoPagoRepository.findByFechaBetween(fechaInicio, fechaFin);
        return creditoPagos.stream()
                .map(creditoPagoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CreditoPagoResponse> listarCreditoPagosPorCreditoOrdenadosPorFecha(Integer idCredito) {
        Credito credito = creditoRepository.findById(idCredito)
                .orElseThrow(() -> new NotFoundException("Crédito no encontrado con ID: " + idCredito));

        List<CreditoPago> creditoPagos = creditoPagoRepository.findByCreditoIdOrderByFechaDesc(idCredito);
        return creditoPagos.stream()
                .map(creditoPagoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarCreditoPago(Integer id) {
        if (!creditoPagoRepository.existsById(id)) {
            throw new NotFoundException("CreditoPago no encontrado con ID: " + id);
        }
        creditoPagoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeCreditoPago(Integer idCredito, Integer idPago) {
        return creditoPagoRepository.existsByCreditoIdCreditoAndPagoIdPago(idCredito, idPago);
    }
}