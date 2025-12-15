package com.bcss.sistemaventas.dto.response;

import com.bcss.sistemaventas.domain.EnumCategoriaGasto;
import com.bcss.sistemaventas.domain.EnumMetodoPago;

import java.time.LocalDateTime;

public record GastoResponse(
        Integer idGasto,
        String descripcion,
        EnumCategoriaGasto tipoGasto,
        Float monto,
        LocalDateTime fecha,
        EnumMetodoPago metodoPago,
        String proveedor,
        String factura,
        String notas,
        Integer idSucursal,
        String nombreSucursal
) {
}
