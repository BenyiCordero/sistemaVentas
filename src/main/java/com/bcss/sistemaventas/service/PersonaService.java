package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.domain.Persona;

import java.util.Optional;

public interface PersonaService {
    Persona save(Persona persona);
    Optional<Persona> findByTelefono(String telefono);
    Optional<Persona> findById(Integer idPersona);
    Optional<Persona> findByNombre(String nombre);
}
