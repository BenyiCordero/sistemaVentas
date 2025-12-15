package com.bcss.sistemaventas.mappers;

import com.bcss.sistemaventas.domain.Gasto;
import com.bcss.sistemaventas.dto.response.GastoResponse;

public class GastoMapper {
    private GastoMapper() {}

    public static GastoResponse toDTO(Gasto gasto) {
        return new GastoResponse(
                gasto.getIdGasto(),
                gasto.getDescripcion(),
                gasto.getTipoGasto(),
                gasto.getMonto(),
                gasto.getFecha(),
                gasto.getMetodoPago(),
                gasto.getProveedor(),
                gasto.getFactura(),
                gasto.getNotas(),
                gasto.getSucursal().getIdSucursal(),
                gasto.getSucursal().getNombre()
        );
    }
}
