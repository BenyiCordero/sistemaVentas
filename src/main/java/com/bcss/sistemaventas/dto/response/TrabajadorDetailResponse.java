package com.bcss.sistemaventas.dto.response;

public record TrabajadorDetailResponse(
    Integer idUsuario,
    String email,
    String rol,
    String nombre,
    String primerApellido,
    String segundoApellido,
    String numeroTelefono,
    Integer idSucursal,
    String sucursalNombre
) {}