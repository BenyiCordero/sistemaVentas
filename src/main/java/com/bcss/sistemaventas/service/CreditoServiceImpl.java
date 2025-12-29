package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.dto.response.CreditoResponse;
import com.bcss.sistemaventas.dto.response.CreditoListResponse;
import com.bcss.sistemaventas.dto.request.CreditoRequest;
import com.bcss.sistemaventas.dto.request.EstadoCreditoRequest;
import com.bcss.sistemaventas.domain.Credito;
import com.bcss.sistemaventas.domain.EnumEstadoCredito;
import com.bcss.sistemaventas.repository.CreditoRepository;
import com.bcss.sistemaventas.repository.ClienteRepository;
import com.bcss.sistemaventas.repository.VentaRepository;
import com.bcss.sistemaventas.mappers.CreditoMapper;
import com.bcss.sistemaventas.exception.NotFoundException;
import com.bcss.sistemaventas.exception.RepeatedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CreditoServiceImpl implements CreditoService {

    private final CreditoRepository creditoRepository;
    private final ClienteRepository clienteRepository;
    private final VentaRepository ventaRepository;
    private final CreditoMapper creditoMapper;

    @Override
    public CreditoResponse crearCredito(CreditoRequest request) {
        // Validar que la venta exista si se proporcionó
        var venta = request.idVenta() != null ? 
                ventaRepository.findById(request.idVenta())
                        .orElseThrow(() -> new NotFoundException("Venta no encontrada con ID: " + request.idVenta())) : 
                null;

        // Calcular fecha de vencimiento
        LocalDate fechaVencimiento = LocalDate.now().plusMonths(request.plazoMeses());

        var credito = Credito.builder()
                .venta(venta)
                .montoInicial(request.montoInicial())
                .saldo(request.montoInicial())
                .tasaInteres(request.tasaInteres())
                .plazoMeses(request.plazoMeses())
                .estado(EnumEstadoCredito.ACTIVO)
                .fechaInicio(LocalDate.now())
                .fechaVencimiento(fechaVencimiento)
                .build();

        var savedCredito = creditoRepository.save(credito);
        
        // Actualizar el estado del cliente
        cliente.setCreditoActivo(true);
        clienteRepository.save(cliente);

        return creditoMapper.mapToResponse(savedCredito);
    }

    @Override
    @Transactional(readOnly = true)
    public CreditoResponse obtenerCreditoPorId(Integer idCredito) {
        var credito = creditoRepository.findById(idCredito)
                .orElseThrow(() -> new NotFoundException("Crédito no encontrado con ID: " + idCredito));
        return creditoMapper.mapToResponse(credito);
    }

    @Override
    @Transactional(readOnly = true)
    public CreditoListResponse listarCreditos() {
        List<CreditoResponse> creditos = creditoRepository.findAll().stream()
                .map(creditoMapper::mapToResponse)
                .collect(Collectors.toList());
        return new CreditoListResponse(creditos);
    }

    @Override
    @Transactional(readOnly = true)
    public CreditoListResponse listarCreditosPorCliente(Integer idCliente) {
        List<CreditoResponse> creditos = creditoRepository.findByClienteIdCliente(idCliente).stream()
                .map(creditoMapper::mapToResponse)
                .collect(Collectors.toList());
        return new CreditoListResponse(creditos);
    }

    @Override
    @Transactional(readOnly = true)
    public CreditoListResponse listarCreditosPorEstado(String estado) {
        try {
            EnumEstadoCredito estadoEnum = EnumEstadoCredito.valueOf(estado.toUpperCase());
            List<CreditoResponse> creditos = creditoRepository.findByEstado(estadoEnum).stream()
                    .map(creditoMapper::mapToResponse)
                    .collect(Collectors.toList());
            return new CreditoListResponse(creditos);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException("Estado de crédito no válido: " + estado);
        }
    }

    @Override
    public CreditoResponse actualizarEstadoCredito(Integer idCredito, EstadoCreditoRequest request) {
        var credito = creditoRepository.findById(idCredito)
                .orElseThrow(() -> new NotFoundException("Crédito no encontrado con ID: " + idCredito));

        try {
            EnumEstadoCredito nuevoEstado = EnumEstadoCredito.valueOf(request.estado().toUpperCase());
            credito.setEstado(nuevoEstado);

            // Si se cancela el crédito, actualizar el estado del cliente
            if (nuevoEstado == EnumEstadoCredito.CANCELADO) {
                credito.getCliente().setCreditoActivo(false);
                clienteRepository.save(credito.getCliente());
            }

            var updatedCredito = creditoRepository.save(credito);
            return creditoMapper.mapToResponse(updatedCredito);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException("Estado de crédito no válido: " + request.estado());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CreditoResponse> obtenerCreditosVencidos() {
        return creditoRepository.findCreditosVencidos().stream()
                .map(creditoMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarCredito(Integer idCredito) {
        var credito = creditoRepository.findById(idCredito)
                .orElseThrow(() -> new NotFoundException("Crédito no encontrado con ID: " + idCredito));

        // Actualizar el estado del cliente si el crédito estaba activo
        if (credito.getEstado() == EnumEstadoCredito.ACTIVO) {
            credito.getCliente().setCreditoActivo(false);
            clienteRepository.save(credito.getCliente());
        }

        creditoRepository.delete(credito);
    }
}