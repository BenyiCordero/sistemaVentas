package com.bcss.sistemaventas.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreditoRequest(
    @NotNull(message = "El ID del cliente es requerido")
    Integer idCliente,
    
    Integer idVenta,
    
    @NotNull(message = "El monto inicial es requerido")
    @DecimalMin(value = "0.01", message = "El monto inicial debe ser mayor a 0")
    Double montoInicial,
    
    @NotNull(message = "La tasa de interés es requerida")
    @DecimalMin(value = "0.0", message = "La tasa de interés no puede ser negativa")
    Double tasaInteres,
    
    @NotNull(message = "El plazo en meses es requerido")
    @Positive(message = "El plazo en meses debe ser positivo")
    Integer plazoMeses,
    
    String notas
) {}