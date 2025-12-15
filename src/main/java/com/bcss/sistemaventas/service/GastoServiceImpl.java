package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.domain.Gasto;
import com.bcss.sistemaventas.domain.Sucursal;
import com.bcss.sistemaventas.dto.request.GastoSaveRequest;
import com.bcss.sistemaventas.dto.response.GastoResponse;
import com.bcss.sistemaventas.mappers.GastoMapper;
import com.bcss.sistemaventas.repository.GastoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GastoServiceImpl implements GastoService {

    private final GastoRepository gastoRepository;
    private final SucursalService sucursalRepository;

    public GastoServiceImpl(GastoRepository gastoRepository, SucursalService sucursalRepository) {
        this.gastoRepository = gastoRepository;
        this.sucursalRepository = sucursalRepository;
    }

    @Override
    public GastoResponse save(GastoSaveRequest request) {

        Sucursal sucursal = sucursalRepository.findById(request.idSucursal());

        Gasto gasto = Gasto.builder()
                .descripcion(request.descripcion())
                .tipoGasto(request.tipoGasto())
                .monto(request.monto())
                .fecha(request.fecha())
                .metodoPago(request.metodoPago())
                .proveedor(request.proveedor())
                .factura(request.factura())
                .notas(request.notas())
                .sucursal(sucursal)
                .build();

        return GastoMapper.toDTO(gastoRepository.save(gasto));
    }

    @Override
    public GastoResponse update(Integer idGasto, GastoSaveRequest request) {

        Gasto gasto = gastoRepository.findById(idGasto)
                .orElseThrow(() -> new RuntimeException("Gasto no encontrado"));

        Sucursal sucursal = sucursalRepository.findById(request.idSucursal());

        gasto.setDescripcion(request.descripcion());
        gasto.setTipoGasto(request.tipoGasto());
        gasto.setMonto(request.monto());
        gasto.setFecha(request.fecha());
        gasto.setMetodoPago(request.metodoPago());
        gasto.setProveedor(request.proveedor());
        gasto.setFactura(request.factura());
        gasto.setNotas(request.notas());
        gasto.setSucursal(sucursal);

        return GastoMapper.toDTO(gastoRepository.save(gasto));
    }

    @Override
    public GastoResponse findById(Integer idGasto) {
        return gastoRepository.findById(idGasto)
                .map(GastoMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Gasto no encontrado"));
    }

    @Override
    public List<GastoResponse> findAll() {
        return gastoRepository.findAll()
                .stream()
                .map(GastoMapper::toDTO)
                .toList();
    }

    @Override
    public void delete(Integer idGasto) {
        if (!gastoRepository.existsById(idGasto)) {
            throw new RuntimeException("Gasto no encontrado");
        }
        gastoRepository.deleteById(idGasto);
    }

    @Override
    public Float totalMesActualPorSucursal(Integer idSucursal) {
        return gastoRepository.totalMesActualPorSucursal(idSucursal);
    }

    @Override
    public Float totalHoyPorSucursal(Integer idSucursal) {
        return gastoRepository.totalHoyPorSucursal(idSucursal);
    }

    @Override
    public Float totalSemanaActualPorSucursal(Integer idSucursal) {
        return gastoRepository.totalSemanaActualPorSucursal(idSucursal);
    }

    @Override
    public Float promedioDiarioMesActual(Integer idSucursal) {
        return gastoRepository.promedioDiarioMesActual(idSucursal);
    }

    @Override
    public Float totalPorRangoFechas(
            Integer idSucursal,
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin
    ) {
        return gastoRepository.totalPorRangoFechas(
                idSucursal,
                fechaInicio,
                fechaFin
        );
    }
}
