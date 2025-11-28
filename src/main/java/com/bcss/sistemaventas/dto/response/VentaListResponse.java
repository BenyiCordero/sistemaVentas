package com.bcss.sistemaventas.dto.response;

import java.util.List;

public record VentaListResponse(
    List<VentaBasicResponse> ventas
) {}