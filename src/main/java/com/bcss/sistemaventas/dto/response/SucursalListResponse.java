package com.bcss.sistemaventas.dto.response;

import java.util.List;

public record SucursalListResponse(
    List<SucursalBasicResponse> sucursales
) {}