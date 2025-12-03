package com.bcss.sistemaventas.dto.response;

import java.util.List;

public record InventarioDetailsListResponse(
    List<InventarioDetailsBasicResponse> detalles
) {}