package com.bcss.sistemaventas.dto.response;

public record SucursalBasicResponse(
    Integer idSucursal,
    String nombre,
    String sucursalKey,
    Boolean activo
) {}