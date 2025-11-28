package com.bcss.sistemaventas.dto.response;

import java.util.List;

public record CreditoListResponse(
    List<CreditoResponse> creditos
) {}