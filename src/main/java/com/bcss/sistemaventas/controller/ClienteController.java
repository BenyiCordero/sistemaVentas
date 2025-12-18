package com.bcss.sistemaventas.controller;

import com.bcss.sistemaventas.dto.request.ClienteRegisterRequest;
import com.bcss.sistemaventas.dto.request.ClienteUpdateRequest;
import com.bcss.sistemaventas.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api//client")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ClienteRegisterRequest clienteRegisterRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(clienteRegisterRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAllClientes(){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAllClientes());
    }

    @GetMapping("/nombre")
    public ResponseEntity<?> findByName(@RequestParam String nombre) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findByName(nombre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable Integer id,@RequestBody ClienteUpdateRequest clienteUpdateRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.updateCliente(id, clienteUpdateRequest));
    }

}
