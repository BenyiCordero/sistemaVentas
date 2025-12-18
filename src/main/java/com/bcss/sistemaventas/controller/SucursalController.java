package com.bcss.sistemaventas.controller;

import com.bcss.sistemaventas.domain.Sucursal;
import com.bcss.sistemaventas.dto.request.SucursalPerUsuarioRequest;
import com.bcss.sistemaventas.dto.request.SucursalRequest;
import com.bcss.sistemaventas.service.SucursalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sucursal")
public class SucursalController {

    private final SucursalService sucursalService;

    public SucursalController(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveSucursal(@RequestBody Sucursal sucursal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sucursalService.save(sucursal));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(sucursalService.findById(id));
    }

    @GetMapping("/getByName")
    public ResponseEntity<?> getByName(@RequestBody SucursalRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(sucursalService.findByName(request.nombre()));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(sucursalService.getAllSucursales());
    }

    @GetMapping("/activas")
    public ResponseEntity<?> getAllActive() {
        return ResponseEntity.status(HttpStatus.OK).body(sucursalService.getAllActive());
    }

    @GetMapping("/inactivas")
    public ResponseEntity<?> getAllInactive() {
        return ResponseEntity.status(HttpStatus.OK).body(sucursalService.getAllInactive());
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Sucursal sucursal) {
        return ResponseEntity.status(HttpStatus.OK).body(sucursalService.updateSucursal(sucursal));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        sucursalService.deleteSucursalById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/getByUsuario")
    public ResponseEntity<?> getByUsuario(@RequestBody SucursalPerUsuarioRequest request) {
        return ResponseEntity.ok(
                sucursalService.getSucursalPerUsuario(request)
        );
    }

}
