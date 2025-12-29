package com.bcss.sistemaventas.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreditoPagoResponse(
    Integer id,
    Integer idCredito,
    Integer idPago,
    BigDecimal monto,
    LocalDateTime fecha,
    LocalDateTime createdAt
) {}