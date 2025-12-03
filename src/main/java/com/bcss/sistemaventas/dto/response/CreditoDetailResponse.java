package com.bcss.sistemaventas.dto.response;

import java.time.LocalDate;

public record CreditoDetailResponse(
    Integer idCredito,
    Integer idCliente,
    String clienteNombre,
    Integer idVenta,
    Double montoInicial,
    Double saldo,
    Double tasaInteres,
    Integer plazoMeses,
    String estado,
    LocalDate fechaInicio,
    LocalDate fechaVencimiento
) {}