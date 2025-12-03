package com.bcss.sistemaventas.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreditoPagoBasicResponse(
    Integer id,
    Integer idCredito,
    BigDecimal monto,
    LocalDateTime fecha
) {}