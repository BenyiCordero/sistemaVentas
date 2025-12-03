package com.bcss.sistemaventas.dto.response;

import java.util.List;

public record CreditoPagoListResponse(
    List<CreditoPagoBasicResponse> creditoPagos,
    Long totalElements,
    Integer totalPages,
    Integer currentPage,
    Boolean hasNext,
    Boolean hasPrevious
) {}