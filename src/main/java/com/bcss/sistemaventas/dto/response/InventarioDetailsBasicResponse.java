package com.bcss.sistemaventas.dto.response;

import java.time.LocalDate;

public record InventarioDetailsBasicResponse(
    Integer idDetalle,
    Integer cantidad,
    String estado,
    Boolean disponible,
    LocalDate fechaAgregado,
    Integer idProducto,
    String productoNombre,
    Integer idInventario,
    String inventarioTitulo
) {}