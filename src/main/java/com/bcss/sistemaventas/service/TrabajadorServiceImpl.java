package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.domain.Persona;
import com.bcss.sistemaventas.domain.Sucursal;
import com.bcss.sistemaventas.domain.Trabajador;
import com.bcss.sistemaventas.dto.request.TrabajadorUpdateRequest;
import com.bcss.sistemaventas.dto.response.TrabajadorDetailResponse;
import com.bcss.sistemaventas.exception.NotFoundException;
import com.bcss.sistemaventas.exception.RepeatedException;
import com.bcss.sistemaventas.repository.TrabajadorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrabajadorServiceImpl implements TrabajadorService {

    private final TrabajadorRepository trabajadorRepository;
    private final PersonaService personaService;
    private final SucursalService sucursalService;

    public TrabajadorServiceImpl(TrabajadorRepository trabajadorRepository, PersonaService personaService, SucursalService sucursalService) {
        this.trabajadorRepository = trabajadorRepository;
        this.personaService = personaService;
        this.sucursalService = sucursalService;
    }

    @Override
    public Trabajador save(Trabajador trabajador) {
        if (trabajadorRepository.existsByEmail(trabajador.getEmail())) throw new RepeatedException("Trabajador ya existente");
        return trabajadorRepository.save(trabajador);
    }

    @Override
    public Optional<Trabajador> findByEmail(String email) {
        Optional<Trabajador> trabajador = trabajadorRepository.findByEmail(email);
        if(trabajador.isPresent()) return trabajador;
        else throw new NotFoundException("Trabajador no encontrado");
    }

    @Override
    public TrabajadorDetailResponse findByEmailDetailed(String email) {
        Optional<Trabajador> trabajador = trabajadorRepository.findByEmail(email);
        Trabajador tr = trabajador.get();
        if(trabajador.isPresent()) return new TrabajadorDetailResponse(tr.getIdUsuario(),
                tr.getEmail(),
                tr.getRol().name(),
                tr.getPersona().getNombre(),
                tr.getPersona().getPrimerApellido(),
                tr.getPersona().getSegundoApellido(),
                tr.getPersona().getNumeroTelefono(),
                tr.getSucursal().getIdSucursal(),
                tr.getSucursal().getNombre());
        else throw new NotFoundException("Trabajador no encontrado");
    }

    @Override
    public List<TrabajadorDetailResponse> findAll() {
        List<Trabajador> trabajadores = trabajadorRepository.findAll();
        List<TrabajadorDetailResponse> trabajadoresResponse = new ArrayList<>();
        for (Trabajador trabajador : trabajadores) {
            // chequeo seguro: si la sucursal es null mostramos "Sin sucursal"
            if (trabajador.getSucursal() == null) {
                trabajadoresResponse.add(new TrabajadorDetailResponse(
                        trabajador.getIdUsuario(),
                        trabajador.getEmail(),
                        trabajador.getRol().name(),
                        trabajador.getPersona() != null ? trabajador.getPersona().getNombre() : null,
                        trabajador.getPersona() != null ? trabajador.getPersona().getPrimerApellido() : null,
                        trabajador.getPersona() != null ? trabajador.getPersona().getSegundoApellido() : null,
                        trabajador.getPersona() != null ? trabajador.getPersona().getNumeroTelefono() : null,
                        0,
                        "Sin sucursal"
                ));
            } else {
                trabajadoresResponse.add(new TrabajadorDetailResponse(
                        trabajador.getIdUsuario(),
                        trabajador.getEmail(),
                        trabajador.getRol().name(),
                        trabajador.getPersona() != null ? trabajador.getPersona().getNombre() : null,
                        trabajador.getPersona() != null ? trabajador.getPersona().getPrimerApellido() : null,
                        trabajador.getPersona() != null ? trabajador.getPersona().getSegundoApellido() : null,
                        trabajador.getPersona() != null ? trabajador.getPersona().getNumeroTelefono() : null,
                        trabajador.getSucursal().getIdSucursal(),
                        trabajador.getSucursal().getNombre()
                ));
            }
        }
        return trabajadoresResponse;
    }

    @Override
    public Optional<Trabajador> findById(Integer idTrabajador) {
        Optional<Trabajador> trabajador = trabajadorRepository.findById(idTrabajador);
        if (trabajador.isPresent()) return trabajador;
        else throw new NotFoundException("Trabajador no encontrado");
    }

    @Override
    public Trabajador updateTrabajador(Integer id, TrabajadorUpdateRequest trabajadorUpdateRequest) {
        if (id == null) {
            throw new NotFoundException("Id del trabajador inválido");
        }

        Optional<Trabajador> optExisting = trabajadorRepository.findById(id);
        if (optExisting.isEmpty()) {
            throw new NotFoundException("Trabajador no encontrado con id: " + id);
        }

        Sucursal sucursal = sucursalService.findById(trabajadorUpdateRequest.idSucursal());
        if(sucursal == null){
            throw new NotFoundException("Sucursal no encontrada");
        }


        Trabajador existing = optExisting.get();

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

        if (trabajadorUpdateRequest.nombre() != null && !trabajadorUpdateRequest.nombre().trim().isEmpty()) {
            personaToUpdate.setNombre(trabajadorUpdateRequest.nombre().trim());
        }
        if (trabajadorUpdateRequest.primerApellido() != null && !trabajadorUpdateRequest.primerApellido().trim().isEmpty()) {
            personaToUpdate.setPrimerApellido(trabajadorUpdateRequest.primerApellido().trim());
        }
        if (trabajadorUpdateRequest.segundoApellido() != null && !trabajadorUpdateRequest.segundoApellido().trim().isEmpty()) {
            personaToUpdate.setSegundoApellido(trabajadorUpdateRequest.segundoApellido().trim());
        }
        if (trabajadorUpdateRequest.numeroTelefono() != null && !trabajadorUpdateRequest.numeroTelefono().trim().isEmpty()) {
            personaToUpdate.setNumeroTelefono(trabajadorUpdateRequest.numeroTelefono().trim());
        }
        if (trabajadorUpdateRequest.rol() != null){
            existing.setRol(trabajadorUpdateRequest.rol());
        }
        if(trabajadorUpdateRequest.idSucursal() != null){
            existing.setSucursal(sucursal);
        }

        Persona savedPersona = personaService.save(personaToUpdate);
        existing.setPersona(savedPersona);

        return trabajadorRepository.save(existing);
    }

    @Override
    public List<Trabajador> findAllTrabajadoresBySucursal(Integer id) {
        return null;
    }

}
