package com.bcss.sistemaventas.repository;

import com.bcss.sistemaventas.domain.Token;
import com.bcss.sistemaventas.domain.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
    Optional<Token> findByToken(String token);
    List<Token> findByTrabajador(Trabajador trabajador);
}
