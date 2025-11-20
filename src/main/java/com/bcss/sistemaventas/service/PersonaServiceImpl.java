package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.domain.Persona;
import com.bcss.sistemaventas.exception.NotFoundException;
import com.bcss.sistemaventas.exception.RepeatedException;
import com.bcss.sistemaventas.repository.PersonaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public Persona save(Persona persona) {
        String telefono = persona.getNumeroTelefono();

        if (persona.getIdPersona() == null) {
            if (personaRepository.existsPersonaByNumeroTelefono(telefono)) {
                throw new RepeatedException("Persona ya existente");
            }
            return personaRepository.save(persona);
        }

        Optional<Persona> personaOptional = personaRepository.findByNumeroTelefono(telefono);
        if (personaOptional.isPresent()) {
            Persona found = personaOptional.get();
            if (!found.getIdPersona().equals(persona.getIdPersona())) {
                throw new RepeatedException("Número de teléfono ya asociado a otra persona");
            }
        }

        return personaRepository.save(persona);
    }

    @Override
    public Optional<Persona> findByTelefono(String telefono) {
        Optional<Persona> persona = personaRepository.findByNumeroTelefono(telefono);
        if(persona.isPresent()) return persona;
        else throw new NotFoundException("Persona no encontrada");
    }

    @Override
    public Optional<Persona> findById(Integer idPersona) {
        Optional<Persona> persona = personaRepository.findById(idPersona);
        if(persona.isPresent()) return persona;
        else throw new NotFoundException("Persona no encontrada");
    }

    @Override
    public Optional<Persona> findByNombre(String nombre) {
        Optional<Persona> persona = personaRepository.findByNombre(nombre);
        if(persona.isPresent()) return persona;
        else throw new NotFoundException("Persona no encontrada");
    }
}
