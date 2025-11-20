package com.bcss.sistemaventas.repository;

import com.bcss.sistemaventas.domain.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador, Integer> {
    Optional<Trabajador> findByEmail(String email);
    boolean existsByEmail(String email);
}
