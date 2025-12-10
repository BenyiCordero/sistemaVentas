package com.bcss.sistemaventas.repository;

import com.bcss.sistemaventas.domain.EnumEstadoVenta;
import com.bcss.sistemaventas.domain.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
    List<Venta> findByEstado(EnumEstadoVenta estado);
    List<Venta> findBySucursalIdSucursal(Integer id);
    List<Venta> findByTrabajadorIdUsuario(Integer id);
    List<Venta> findByClienteIdCliente(Integer id);
    @Query("SELECT COALESCE(SUM(v.totalVenta), 0) FROM Venta v " +
            "WHERE MONTH(v.fechaVenta) = MONTH(CURRENT_DATE) " +
            "AND YEAR(v.fechaVenta) = YEAR(CURRENT_DATE)")
    Double sumTotalMesActual();
}
