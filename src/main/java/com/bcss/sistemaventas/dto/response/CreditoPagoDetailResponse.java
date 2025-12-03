package com.bcss.sistemaventas.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreditoPagoDetailResponse(
    Integer id,
    Integer idCredito,
    Integer idPago,
    BigDecimal monto,
    LocalDateTime fecha,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    CreditoBasicResponse credito
) {}