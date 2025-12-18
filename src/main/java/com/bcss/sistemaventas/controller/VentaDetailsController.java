package com.bcss.sistemaventas.controller;

import com.bcss.sistemaventas.dto.request.VentaDetailsRequest;
import com.bcss.sistemaventas.service.VentaDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sellDetails")
public class VentaDetailsController {

    private final VentaDetailsService service;

    public VentaDetailsController(VentaDetailsService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @GetMapping("venta/{id}")
    public ResponseEntity<?> getByVenta(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getByVenta(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody VentaDetailsRequest ventaDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(ventaDetails));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody VentaDetailsRequest ventaDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, ventaDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
