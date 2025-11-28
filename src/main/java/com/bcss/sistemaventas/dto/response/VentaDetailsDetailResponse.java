package com.bcss.sistemaventas.dto.response;

public record VentaDetailsDetailResponse(
    Integer idVentaDetails,
    Integer cantidad,
    Double precio,
    Double subtotal,
    Integer idProducto,
    String productoNombre,
    String productoMarca,
    String productoModelo,
    Integer idVenta
) {}