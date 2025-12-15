package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.domain.Gasto;
import com.bcss.sistemaventas.dto.request.GastoSaveRequest;
import com.bcss.sistemaventas.dto.response.GastoResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface GastoService {
    GastoResponse save(GastoSaveRequest request);
    GastoResponse update(Integer idGasto, GastoSaveRequest request);
    GastoResponse findById(Integer idGasto);
    List<GastoResponse> findAll();
    void delete(Integer idGasto);
    Float totalMesActualPorSucursal(Integer idSucursal);
    Float totalHoyPorSucursal(Integer idSucursal);
    Float totalSemanaActualPorSucursal(Integer idSucursal);
    Float promedioDiarioMesActual(Integer idSucursal);
    Float totalPorRangoFechas(
            Integer idSucursal,
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin
    );
}
