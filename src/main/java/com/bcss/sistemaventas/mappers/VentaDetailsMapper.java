package com.bcss.sistemaventas.mappers;

import com.bcss.sistemaventas.domain.VentaDetails;
import com.bcss.sistemaventas.dto.response.VentaDetailsBasicResponse;
import com.bcss.sistemaventas.dto.response.VentaDetailsDetailResponse;
import org.springframework.stereotype.Component;

@Component
public class VentaDetailsMapper {

    public VentaDetailsBasicResponse mapToBasic(VentaDetails detalle) {
        return new VentaDetailsBasicResponse(
            detalle.getIdVentaDetails(),
            detalle.getCantidad(),
            detalle.getPrecio(),
            detalle.getSubtotal(),
            detalle.getProducto().getIdProducto(),
            detalle.getProducto().getNombre(),
            detalle.getVenta().getIdVenta()
        );
    }

    public VentaDetailsDetailResponse mapToDetail(VentaDetails detalle) {
        return new VentaDetailsDetailResponse(
            detalle.getIdVentaDetails(),
            detalle.getCantidad(),
            detalle.getPrecio(),
            detalle.getSubtotal(),
            detalle.getProducto().getIdProducto(),
            detalle.getProducto().getNombre(),
            detalle.getProducto().getMarca(),
            detalle.getProducto().getModelo(),
            detalle.getVenta().getIdVenta()
        );
    }
}