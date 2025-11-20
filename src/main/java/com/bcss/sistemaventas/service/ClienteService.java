package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.domain.Cliente;
import com.bcss.sistemaventas.dto.request.ClienteRegisterRequest;
import com.bcss.sistemaventas.dto.request.ClienteUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    Cliente save(ClienteRegisterRequest clienteRegisterRequest);
    List<Cliente> findByName(String nombre);
    Optional<Cliente> findById(Integer idCliente);
    List<Cliente> findAllClientes();
    Cliente updateCliente(Integer id, ClienteUpdateRequest clienteUpdateRequest);
}
