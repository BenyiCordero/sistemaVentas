package com.bcss.sistemaventas.dto.response;

import java.time.LocalDate;

public record InventarioDetailsDetailResponse(
    Integer idDetalle,
    Integer cantidad,
    String estado,
    Boolean disponible,
    LocalDate fechaAgregado,
    Integer idProducto,
    String productoNombre,
    String productoMarca,
    String productoModelo,
    Integer idInventario,
    String inventarioTitulo
) {}