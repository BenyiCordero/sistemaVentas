package com.bcss.sistemaventas.controller;

import com.bcss.sistemaventas.domain.Producto;
import com.bcss.sistemaventas.dto.request.ProductoUpdateRequest;
import com.bcss.sistemaventas.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Producto producto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(producto));
    }

    @GetMapping("/nombre")
    public ResponseEntity<?> findByNombre(@RequestParam String nombre) {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.findByNombre(nombre));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ProductoUpdateRequest productoUpdateRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.update(id,productoUpdateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.deleteById(id));
    }

    @GetMapping
    public ResponseEntity<List<Producto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.findAll());
    }

    @GetMapping("/activo")
    public ResponseEntity<?> findByActivo() {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.findAllActivo());
    }

    @GetMapping("/inactivo")
    public ResponseEntity<?> findByInactivo() {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.findAllInactivo());
    }

}
