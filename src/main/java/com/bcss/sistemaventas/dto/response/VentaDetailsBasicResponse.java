package com.bcss.sistemaventas.dto.response;

public record VentaDetailsBasicResponse(
    Integer idVentaDetails,
    Integer cantidad,
    Double precio,
    Double subtotal,
    Integer idProducto,
    String productoNombre,
    Integer idVenta
) {}