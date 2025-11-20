package com.bcss.sistemaventas.repository;

import com.bcss.sistemaventas.domain.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Integer> {
    Optional<Inventario> findBySucursal_idSucursal(Integer id);

}
