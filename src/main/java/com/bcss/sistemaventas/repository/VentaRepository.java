package com.bcss.sistemaventas.repository;

import com.bcss.sistemaventas.domain.EnumEstadoVenta;
import com.bcss.sistemaventas.domain.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
    List<Venta> findByEstado(EnumEstadoVenta estado);
    List<Venta> findBySucursalIdSucursal(Integer id);
    List<Venta> findByTrabajadorIdUsuario(Integer id);
    List<Venta> findByClienteIdCliente(Integer id);
@Query("""
    SELECT COALESCE(SUM(v.totalVenta), 0)
    FROM Venta v
    WHERE v.sucursal.idSucursal = :idSucursal
      AND MONTH(v.fechaVenta) = MONTH(CURRENT_DATE)
      AND YEAR(v.fechaVenta) = YEAR(CURRENT_DATE)
      AND v.metodoPago != 'CREDITO'
""")
    Float sumTotalMesActualContado(@Param("idSucursal") Integer idSucursal);

    @Query("""
    SELECT COALESCE(SUM(v.totalVenta), 0)
    FROM Venta v
    WHERE v.sucursal.idSucursal = :idSucursal
      AND MONTH(v.fechaVenta) = MONTH(CURRENT_DATE)
      AND YEAR(v.fechaVenta) = YEAR(CURRENT_DATE)
      AND v.metodoPago = 'CREDITO'
""")
    Float sumTotalMesActualCreditos(@Param("idSucursal") Integer idSucursal);

    @Query("""
    SELECT COALESCE(SUM(v.totalVenta), 0)
    FROM Venta v
    WHERE v.sucursal.idSucursal = :idSucursal
      AND MONTH(v.fechaVenta) = MONTH(CURRENT_DATE)
      AND YEAR(v.fechaVenta) = YEAR(CURRENT_DATE)
""")
Float sumTotalMesActual(@Param("idSucursal") Integer idSucursal);

    @Query("""
    SELECT COALESCE(SUM(cp.monto), 0)
    FROM CreditoPago cp
    JOIN cp.credito c
    WHERE c.venta.sucursal.idSucursal = :idSucursal
      AND MONTH(cp.fecha) = MONTH(CURRENT_DATE)
      AND YEAR(cp.fecha) = YEAR(CURRENT_DATE)
""")
    Float sumPagosCreditosMesActual(@Param("idSucursal") Integer idSucursal);

}
