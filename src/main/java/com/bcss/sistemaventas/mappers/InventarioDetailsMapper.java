package com.bcss.sistemaventas.mappers;

import com.bcss.sistemaventas.domain.InventarioDetails;
import com.bcss.sistemaventas.dto.response.InventarioDetailsBasicResponse;
import com.bcss.sistemaventas.dto.response.InventarioDetailsDetailResponse;
import org.springframework.stereotype.Component;

@Component
public class InventarioDetailsMapper {

    public InventarioDetailsBasicResponse mapToBasic(InventarioDetails detalle) {
        return new InventarioDetailsBasicResponse(
            detalle.getIdDetalle(),
            detalle.getCantidad(),
            detalle.getEstado(),
            detalle.isDisponible(),
            detalle.getFechaAgregado(),
            detalle.getProducto().getIdProducto(),
            detalle.getProducto().getNombre(),
            detalle.getInventario().getIdInventario(),
            detalle.getInventario().getTitulo()
        );
    }

    public InventarioDetailsDetailResponse mapToDetail(InventarioDetails detalle) {
        return new InventarioDetailsDetailResponse(
            detalle.getIdDetalle(),
            detalle.getCantidad(),
            detalle.getEstado(),
            detalle.isDisponible(),
            detalle.getFechaAgregado(),
            detalle.getProducto().getIdProducto(),
            detalle.getProducto().getNombre(),
            detalle.getProducto().getMarca(),
            detalle.getProducto().getModelo(),
            detalle.getInventario().getIdInventario(),
            detalle.getInventario().getTitulo()
        );
    }
}