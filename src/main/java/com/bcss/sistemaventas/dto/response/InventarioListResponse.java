package com.bcss.sistemaventas.dto.response;

import java.util.List;

public record InventarioListResponse(
    List<InventarioBasicResponse> inventarios
) {}