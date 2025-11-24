package com.bcss.sistemaventas.repository;

import com.bcss.sistemaventas.domain.Venta;
import com.bcss.sistemaventas.domain.VentaDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaDetailsRepository extends JpaRepository<VentaDetails, Integer> {
    List<VentaDetails> findByVenta(Venta venta);
}
