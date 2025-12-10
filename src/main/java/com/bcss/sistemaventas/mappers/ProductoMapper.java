package com.bcss.sistemaventas.mappers;

import com.bcss.sistemaventas.domain.Producto;
import com.bcss.sistemaventas.dto.response.ProductoBasicResponse;
import com.bcss.sistemaventas.dto.response.ProductoDetailResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public ProductoBasicResponse mapToBasic(Producto producto) {
        return new ProductoBasicResponse(
            producto.getIdProducto(),
            producto.getNombre(),
            producto.getMarca(),
            producto.getModelo(),
            producto.getPrecio(),
            producto.getActivo()
        );
    }

    public ProductoDetailResponse mapToDetail(Producto producto) {
        return new ProductoDetailResponse(
            producto.getIdProducto(),
            producto.getNombre(),
            producto.getDescripcion(),
            producto.getMarca(),
            producto.getModelo(),
            producto.getImei(),
            producto.getPrecio(),
            producto.getCosto(),
            producto.getActivo()
        );
    }
}