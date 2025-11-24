package com.bcss.sistemaventas.dto.request;

public record VentaDetailsRequest(
        Integer idProducto,
        Integer idVenta,
        Integer cantidad,
        Double precio,
        Double subtotal
) {
}
