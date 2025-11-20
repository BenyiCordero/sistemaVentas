package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.domain.Producto;
import com.bcss.sistemaventas.dto.request.ProductoUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    Producto save(Producto producto);
    List<Producto> findByNombre(String nombre);
    Optional<Producto> findById(Integer idProducto);
    Producto update(Integer id, ProductoUpdateRequest productoUpdateRequest);
    Boolean deleteById(Integer idProducto);
    List<Producto> findAll();
    List<Producto> findAllActivo();
    List<Producto> findAllInactivo();
}
