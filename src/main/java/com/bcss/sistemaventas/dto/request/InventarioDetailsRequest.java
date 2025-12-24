package com.bcss.sistemaventas.dto.request;

public record InventarioDetailsRequest(
        Integer cantidad,
        String estado,
        Boolean disponible,
        Integer idProducto,
        Integer idInventario,
        String metodoPago
) {
}
