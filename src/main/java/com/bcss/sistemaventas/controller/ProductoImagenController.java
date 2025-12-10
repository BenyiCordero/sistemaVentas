package com.bcss.sistemaventas.controller;

import com.bcss.sistemaventas.domain.ProductoImagen;
import com.bcss.sistemaventas.service.ProductoImagenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/v1/productos/{idProducto}/imagenes")
@RequiredArgsConstructor
public class ProductoImagenController {

    private final ProductoImagenService productoImagenService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    public ResponseEntity<ProductoImagen> agregarImagenProducto(
            @PathVariable @Positive Integer idProducto,
            @RequestParam @NotBlank String urlImagen,
            @RequestParam @NotBlank String nombreArchivo,
            @RequestParam @NotNull Integer tamañoArchivo,
            @RequestParam String tipoArchivo,
            @RequestParam(defaultValue = "false") Boolean esPrincipal) {
        
        ProductoImagen imagen = productoImagenService.agregarImagenProducto(
                idProducto, urlImagen, nombreArchivo, tamañoArchivo, tipoArchivo, esPrincipal);
        
        return new ResponseEntity<>(imagen, HttpStatus.CREATED);
    }

    @GetMapping("/{idImagen}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    public ResponseEntity<ProductoImagen> obtenerImagenPorId(
            @PathVariable Integer idProducto,
            @PathVariable @Positive Integer idImagen) {
        
        ProductoImagen imagen = productoImagenService.obtenerImagenPorId(idImagen);
        return ResponseEntity.ok(imagen);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    public ResponseEntity<List<ProductoImagen>> listarImagenesPorProducto(
            @PathVariable @Positive Integer idProducto) {
        
        List<ProductoImagen> imagenes = productoImagenService.listarImagenesPorProducto(idProducto);
        return ResponseEntity.ok(imagenes);
    }

    @GetMapping("/principal")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    public ResponseEntity<ProductoImagen> obtenerImagenPrincipalPorProducto(
            @PathVariable @Positive Integer idProducto) {
        
        ProductoImagen imagen = productoImagenService.obtenerImagenPrincipalPorProducto(idProducto);
        return ResponseEntity.ok(imagen);
    }

    @GetMapping("/ordenadas")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    public ResponseEntity<List<ProductoImagen>> listarImagenesPorProductoOrdenadas(
            @PathVariable @Positive Integer idProducto) {
        
        List<ProductoImagen> imagenes = productoImagenService.listarImagenesPorProductoOrdenadas(idProducto);
        return ResponseEntity.ok(imagenes);
    }

    @PutMapping("/{idImagen}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    public ResponseEntity<ProductoImagen> actualizarImagen(
            @PathVariable Integer idProducto,
            @PathVariable @Positive Integer idImagen,
            @RequestParam(required = false) String urlImagen,
            @RequestParam(required = false) String nombreArchivo,
            @RequestParam(required = false) Integer tamañoArchivo,
            @RequestParam(required = false) String tipoArchivo,
            @RequestParam(required = false) Boolean esPrincipal) {
        
        ProductoImagen imagen = productoImagenService.actualizarImagen(
                idImagen, urlImagen, nombreArchivo, tamañoArchivo, tipoArchivo, esPrincipal);
        
        return ResponseEntity.ok(imagen);
    }

    @PutMapping("/{idImagen}/principal")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    public ResponseEntity<ProductoImagen> establecerImagenPrincipal(
            @PathVariable Integer idProducto,
            @PathVariable @Positive Integer idImagen) {
        
        ProductoImagen imagen = productoImagenService.establecerImagenPrincipal(idImagen);
        return ResponseEntity.ok(imagen);
    }

    @DeleteMapping("/{idImagen}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    public ResponseEntity<Void> eliminarImagen(
            @PathVariable Integer idProducto,
            @PathVariable @Positive Integer idImagen) {
        
        productoImagenService.eliminarImagen(idImagen);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarImagenesPorProducto(
            @PathVariable @Positive Integer idProducto) {
        
        productoImagenService.eliminarImagenesPorProducto(idProducto);
        return ResponseEntity.noContent().build();
    }
}