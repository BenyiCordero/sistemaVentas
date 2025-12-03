package com.bcss.sistemaventas.dto.response;

public record SucursalDetailResponse(
    Integer idSucursal,
    String nombre,
    String sucursalKey,
    Boolean activo
) {}