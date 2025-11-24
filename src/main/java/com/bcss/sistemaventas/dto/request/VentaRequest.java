package com.bcss.sistemaventas.dto.request;

public record VentaRequest(
        Integer idSucursal,
        Integer idCliente,
        Integer idTrabajador,
        Double totalVenta,
        Double descuento,
        Double impuesto,
        String notas
) {
}
