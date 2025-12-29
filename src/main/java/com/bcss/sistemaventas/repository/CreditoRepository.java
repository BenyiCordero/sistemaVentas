package com.bcss.sistemaventas.repository;

import com.bcss.sistemaventas.domain.Credito;
import com.bcss.sistemaventas.domain.EnumEstadoCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditoRepository extends JpaRepository<Credito, Integer> {
    
List<Credito> findByEstado(EnumEstadoCredito estado);
    
    List<Credito> findByVentaClienteIdCliente(Integer idCliente);
    
    List<Credito> findByVentaClienteIdClienteAndEstado(Integer idCliente, EnumEstadoCredito estado);
    
    @Query("SELECT c FROM Credito c WHERE c.saldo > 0 AND c.estado = :estado")
    List<Credito> findCreditosActivosConSaldo(@Param("estado") EnumEstadoCredito estado);
    
    @Query("SELECT c FROM Credito c WHERE c.fechaVencimiento < CURRENT_DATE AND c.estado = com.bcss.sistemaventas.domain.EnumEstadoCredito.ACTIVO")
    List<Credito> findCreditosVencidos();
    
    Optional<Credito> findByVentaIdVenta(Integer idVenta);
    
    boolean existsByVentaClienteIdClienteAndEstado(Integer idCliente, EnumEstadoCredito estado);
}