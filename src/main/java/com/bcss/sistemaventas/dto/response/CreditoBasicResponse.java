package com.bcss.sistemaventas.dto.response;

import java.time.LocalDate;

public record CreditoBasicResponse(
    Integer idCredito,
    Double montoInicial,
    Double saldo,
    Double tasaInteres,
    Integer plazoMeses,
    String estado,
    LocalDate fechaInicio,
    LocalDate fechaVencimiento
) {}