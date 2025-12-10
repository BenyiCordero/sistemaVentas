package com.bcss.sistemaventas.dto.request;

import jakarta.validation.constraints.NotNull;

public record EstadoCreditoRequest(
    @NotNull(message = "El estado del crédito es requerido")
    String estado
) {}