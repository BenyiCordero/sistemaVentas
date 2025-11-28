package com.bcss.sistemaventas.dto.response;

public record TrabajadorBasicResponse(
    Integer idUsuario,
    String email,
    String rol,
    String nombreCompleto,
    String sucursalNombre
) {}