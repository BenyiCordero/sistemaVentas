package com.bcss.sistemaventas.repository;

import com.bcss.sistemaventas.domain.InventarioDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventarioDetailsRepository extends JpaRepository<InventarioDetails, Integer> {
    List<InventarioDetails> findByProductoIdProductoAndDisponibleTrue(Integer idProducto);
    List<InventarioDetails> findByInventarioIdInventarioAndDisponibleTrue(Integer idInventario);
    List<InventarioDetails> findByDisponible(Boolean disponible);
}
