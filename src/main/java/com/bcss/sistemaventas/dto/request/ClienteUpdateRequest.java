package com.bcss.sistemaventas.dto.request;

public record ClienteUpdateRequest(
        Boolean creditoActivo,
        String nombre,
        String primerApellido,
        String segundoApellido,
        String numeroTelefono
) {
}
