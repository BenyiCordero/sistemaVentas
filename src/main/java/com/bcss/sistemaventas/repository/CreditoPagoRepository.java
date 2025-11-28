package com.bcss.sistemaventas.repository;

import com.bcss.sistemaventas.domain.CreditoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CreditoPagoRepository extends JpaRepository<CreditoPago, Integer> {
    
    List<CreditoPago> findByCreditoIdCredito(Integer idCredito);
    
    List<CreditoPago> findByPagoIdPago(Integer idPago);
    
    Optional<CreditoPago> findByCreditoIdCreditoAndPagoIdPago(Integer idCredito, Integer idPago);
    
    @Query("SELECT cp FROM CreditoPago cp WHERE cp.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<CreditoPago> findByFechaBetween(@Param("fechaInicio") LocalDateTime fechaInicio, 
                                         @Param("fechaFin") LocalDateTime fechaFin);
    
    @Query("SELECT SUM(cp.monto) FROM CreditoPago cp WHERE cp.credito.idCredito = :idCredito")
    java.math.BigDecimal sumMontoByCreditoId(@Param("idCredito") Integer idCredito);
    
    @Query("SELECT cp FROM CreditoPago cp WHERE cp.credito.idCredito = :idCredito ORDER BY cp.fecha DESC")
    List<CreditoPago> findByCreditoIdOrderByFechaDesc(@Param("idCredito") Integer idCredito);
    
    boolean existsByCreditoIdCreditoAndPagoIdPago(Integer idCredito, Integer idPago);
}