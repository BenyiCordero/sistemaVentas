package com.bcss.sistemaventas.dto.request;

public record ProductoUpdateRequest(
        String nombre,
        String descripcion,
        String imei,
        String marca,
        String modelo,
        String email,
        Float precio,
        Float costo,
        Boolean activo
) {
}
