package com.bcss.sistemaventas.dto.response;

import java.util.List;

public record VentaDetailsListResponse(
    List<VentaDetailsBasicResponse> detalles
) {}