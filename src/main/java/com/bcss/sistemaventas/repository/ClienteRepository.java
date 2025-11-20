package com.bcss.sistemaventas.repository;

import com.bcss.sistemaventas.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByPersonaNombreStartingWithIgnoreCase(String nombre);
}
