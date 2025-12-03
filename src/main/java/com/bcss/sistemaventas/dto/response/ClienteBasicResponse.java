package com.bcss.sistemaventas.dto.response;

public record ClienteBasicResponse(
    Integer idCliente,
    String nombreCompleto,
    String telefono,
    Boolean creditoActivo
) {}