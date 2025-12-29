package com.bcss.sistemaventas.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreditoPagoDetalleResponse(
    Integer id,
    Integer idCredito,
    Integer idVenta,
    Integer idCliente,
    String clienteNombre,
    BigDecimal montoPago,
    BigDecimal saldoAnterior,
    BigDecimal saldoActual,
    String metodoPago,
    LocalDateTime fechaPago,
    String estadoCredito,
    LocalDateTime createdAt
) {}