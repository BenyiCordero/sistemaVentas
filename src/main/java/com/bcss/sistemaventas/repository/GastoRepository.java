package com.bcss.sistemaventas.repository;

import com.bcss.sistemaventas.domain.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Integer> {
    @Query("""
    SELECT COALESCE(SUM(g.monto), 0)
    FROM Gasto g
    WHERE g.sucursal.idSucursal = :idSucursal
      AND g.fecha BETWEEN :fechaInicio AND :fechaFin
    """)
    Float totalPorRangoFechas(
            @Param("idSucursal") Integer idSucursal,
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin
    );


    @Query("""
    SELECT COALESCE(SUM(g.monto), 0)
    FROM Gasto g
    WHERE g.sucursal.idSucursal = :idSucursal
      AND MONTH(g.fecha) = MONTH(CURRENT_DATE)
      AND YEAR(g.fecha) = YEAR(CURRENT_DATE)
    """)
    Float totalMesActualPorSucursal(@Param("idSucursal") Integer idSucursal);

    @Query("""
    SELECT COALESCE(SUM(g.monto), 0)
    FROM Gasto g
    WHERE g.sucursal.idSucursal = :idSucursal
      AND DATE(g.fecha) = CURRENT_DATE
    """)
    Float totalHoyPorSucursal(@Param("idSucursal") Integer idSucursal);

    @Query("""
    SELECT COALESCE(SUM(g.monto), 0)
    FROM Gasto g
    WHERE g.sucursal.idSucursal = :idSucursal
      AND YEARWEEK(g.fecha, 1) = YEARWEEK(CURRENT_DATE, 1)
    """)
    Float totalSemanaActualPorSucursal(@Param("idSucursal") Integer idSucursal);

    @Query("""
    SELECT COALESCE(AVG(d.totalDia), 0)
    FROM (
        SELECT SUM(g.monto) AS totalDia
        FROM Gasto g
        WHERE g.sucursal.idSucursal = :idSucursal
          AND MONTH(g.fecha) = MONTH(CURRENT_DATE)
          AND YEAR(g.fecha) = YEAR(CURRENT_DATE)
        GROUP BY DATE(g.fecha)
    ) d
    """)
    Float promedioDiarioMesActual(@Param("idSucursal") Integer idSucursal);

}
