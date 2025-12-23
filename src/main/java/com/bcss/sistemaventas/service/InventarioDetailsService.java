package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.domain.InventarioDetails;
import com.bcss.sistemaventas.dto.request.InventarioDetailsRequest;

import java.util.List;

public interface InventarioDetailsService {
    List<InventarioDetails> getAll();
    List<InventarioDetails> getAllDisponibles();
    List<InventarioDetails> getAllNoDisponibles();
    InventarioDetails getById(Integer id);
    InventarioDetails save(InventarioDetailsRequest inventarioDetails);
    InventarioDetails update(Integer id, InventarioDetailsRequest inventarioDetails);
    void delete(Integer id);
    List<InventarioDetails> getByInventario(Integer idInventario);
    List<InventarioDetails> getByProducto(Integer idProducto);
    Float getTotalComprasMes(Integer idSucursal);
}
