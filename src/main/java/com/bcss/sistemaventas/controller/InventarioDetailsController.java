package com.bcss.sistemaventas.controller;

import com.bcss.sistemaventas.dto.request.InventarioDetailsRequest;
import com.bcss.sistemaventas.service.InventarioDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventoryDetails")
public class InventarioDetailsController {

    private final InventarioDetailsService service;

    public InventarioDetailsController(InventarioDetailsService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/disponibles")
    public ResponseEntity<?> getAllDisponibles() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllDisponibles());
    }

    @GetMapping("/noDisponibles")
    public ResponseEntity<?> getAllNoDisponibles() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllNoDisponibles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody InventarioDetailsRequest inventarioDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(inventarioDetails));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody InventarioDetailsRequest inventarioDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, inventarioDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/inventario/{idInventario}")
    public ResponseEntity<?> getByInventario(@PathVariable Integer idInventario) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getByInventario(idInventario));
    }

    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<?> getByProducto(@PathVariable Integer idProducto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getByProducto(idProducto));
    }

    @GetMapping("/total-mes")
    public ResponseEntity<?> getTotalMes() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getTotalComprasMes());
    }

}
