package com.bcss.sistemaventas.dto.response;

import java.util.List;

public record ProductoListResponse(
    List<ProductoBasicResponse> productos
) {}