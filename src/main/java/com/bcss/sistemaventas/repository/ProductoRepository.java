package com.bcss.sistemaventas.repository;

import com.bcss.sistemaventas.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByNombreStartingWithIgnoreCase(String nombre);
    List<Producto> findByActivo(Boolean activo);
}
