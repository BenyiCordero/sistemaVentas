package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.domain.Cliente;
import com.bcss.sistemaventas.domain.Persona;
import com.bcss.sistemaventas.dto.request.ClienteRegisterRequest;
import com.bcss.sistemaventas.dto.request.ClienteUpdateRequest;
import com.bcss.sistemaventas.exception.NotFoundException;
import com.bcss.sistemaventas.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final PersonaService personaService;

    public ClienteServiceImpl(ClienteRepository clienteRepository, PersonaService personaService) {
        this.clienteRepository = clienteRepository;
        this.personaService = personaService;
    }

    @Transactional
    public Cliente save(ClienteRegisterRequest clienteRegisterRequest) {
        Persona persona = Persona
                .builder()
                .nombre(clienteRegisterRequest.nombre())
                .primerApellido(clienteRegisterRequest.primerApellido())
                .segundoApellido(clienteRegisterRequest.segundoApellido())
                .numeroTelefono(clienteRegisterRequest.numeroTelefono())
                .build();
        Persona personaSaved = personaService.save(persona);
        Cliente cliente = Cliente
                .builder()
                .creditoActivo(clienteRegisterRequest.creditoActivo())
                .persona(personaSaved)
                .build();
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> findByName(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NotFoundException("Nombre proporcionado inválido");
        }

        String pref = nombre.trim();
        List<Cliente> coincidencias = clienteRepository.findByPersonaNombreStartingWithIgnoreCase(pref);

        if (coincidencias == null || coincidencias.isEmpty()) {
            throw new NotFoundException("No se encontraron clientes con el prefijo: " + pref);
        }
        return coincidencias;
    }

    @Override
    public Optional<Cliente> findById(Integer idCliente) {
        Optional<Cliente> cliente = clienteRepository.findById(idCliente);
        if (!cliente.isPresent()) throw new NotFoundException("Cliente no encontrado");
        return cliente;
    }

    @Override
    public List<Cliente> findAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        if (clientes.isEmpty()) throw new NotFoundException("Clientes no encontrado");
        return clientes;
    }

    @Transactional
    public Cliente updateCliente(Integer id, ClienteUpdateRequest dto) {
        if (id == null) {
            throw new NotFoundException("Id de cliente inválido");
        }

        Optional<Cliente> optExisting = clienteRepository.findById(id);
        if (optExisting.isEmpty()) {
            throw new NotFoundException("Cliente no encontrado con id: " + id);
        }

        Cliente existing = optExisting.get();

        if (dto.creditoActivo() != null) {
            existing.setCreditoActivo(dto.creditoActivo());
        }

        Persona personaToUpdate;

        if (existing.getPersona() != null && existing.getPersona().getIdPersona() != null) {
            Integer personaId = existing.getPersona().getIdPersona();
            Optional<Persona> optPersona = personaService.findById(personaId);
            if (optPersona.isPresent()) {
                personaToUpdate = optPersona.get();
            } else {
                personaToUpdate = Persona.builder().idPersona(personaId).build();
            }
        } else {
            personaToUpdate = Persona.builder().build();
        }

        if (dto.nombre() != null && !dto.nombre().trim().isEmpty()) {
            personaToUpdate.setNombre(dto.nombre().trim());
        }
        if (dto.primerApellido() != null && !dto.primerApellido().trim().isEmpty()) {
            personaToUpdate.setPrimerApellido(dto.primerApellido().trim());
        }
        if (dto.segundoApellido() != null && !dto.segundoApellido().trim().isEmpty()) {
            personaToUpdate.setSegundoApellido(dto.segundoApellido().trim());
        }
        if (dto.numeroTelefono() != null && !dto.numeroTelefono().trim().isEmpty()) {
            personaToUpdate.setNumeroTelefono(dto.numeroTelefono().trim());
        }

        Persona savedPersona = personaService.save(personaToUpdate);
        existing.setPersona(savedPersona);

        return clienteRepository.save(existing);
    }

}
