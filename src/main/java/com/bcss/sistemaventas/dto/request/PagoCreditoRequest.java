package com.bcss.sistemaventas.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import com.bcss.sistemaventas.domain.EnumMetodoPago;

public record PagoCreditoRequest(
    @NotNull(message = "El ID del crédito es requerido")
    Integer idCredito,
    
    @NotNull(message = "El monto es requerido")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
    Double monto,
    
    @NotNull(message = "El método de pago es requerido")
    EnumMetodoPago metodoPago,
    
    String notas
) {}