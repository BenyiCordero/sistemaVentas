package com.bcss.sistemaventas.repository;

import com.bcss.sistemaventas.domain.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta,Integer> {
}
