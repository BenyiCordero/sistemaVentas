package com.bcss.sistemaventas.repository;

import com.bcss.sistemaventas.domain.Pago;
import com.bcss.sistemaventas.domain.EnumMetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {
    
    List<Pago> findByMetodoPago(EnumMetodoPago metodoPago);
    
    List<Pago> findByFechaPagoBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    
    @Query("SELECT p FROM Pago p WHERE p.fechaPago >= :fecha ORDER BY p.fechaPago ASC")
    List<Pago> findPagosFuturos(@Param("fecha") LocalDateTime fecha);
}