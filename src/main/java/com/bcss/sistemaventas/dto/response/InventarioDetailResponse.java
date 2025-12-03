package com.bcss.sistemaventas.dto.response;

import java.time.LocalDate;

public record InventarioDetailResponse(
    Integer idInventario,
    String titulo,
    String descripcion,
    LocalDate fechaCreacion,
    Integer idSucursal,
    String sucursalNombre
) {}