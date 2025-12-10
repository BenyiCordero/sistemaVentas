package com.bcss.sistemaventas.repository;

import com.bcss.sistemaventas.domain.InventarioDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventarioDetailsRepository extends JpaRepository<InventarioDetails, Integer> {
    List<InventarioDetails> findByProductoIdProductoAndDisponibleTrue(Integer idProducto);
    List<InventarioDetails> findByInventarioIdInventarioAndDisponibleTrue(Integer idInventario);
    List<InventarioDetails> findByDisponible(Boolean disponible);
    @Query("SELECT COALESCE(SUM(p.costo), 0) " +
            "FROM Producto p " +
            "JOIN InventarioDetails id ON p.idProducto = id.producto.idProducto " +
            "WHERE MONTH(id.fechaAgregado) = MONTH(CURRENT_DATE()) " +
            "AND YEAR(id.fechaAgregado) = YEAR(CURRENT_DATE())")
    Double sumTotalMesActual();
}
