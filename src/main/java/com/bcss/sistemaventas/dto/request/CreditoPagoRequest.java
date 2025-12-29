package com.bcss.sistemaventas.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreditoPagoRequest(
    @NotNull(message = "El ID del crédito es requerido")
    Integer idCredito,
    Integer idPago,
    @NotNull(message = "El monto es requerido")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
    BigDecimal monto
) {}