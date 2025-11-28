package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.domain.ProductoImagen;
import com.bcss.sistemaventas.repository.ProductoImagenRepository;
import com.bcss.sistemaventas.repository.ProductoRepository;
import com.bcss.sistemaventas.exception.NotFoundException;
import com.bcss.sistemaventas.exception.RepeatedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductoImagenServiceImpl implements ProductoImagenService {

    private final ProductoImagenRepository productoImagenRepository;
    private final ProductoRepository productoRepository;

    @Override
    public ProductoImagen agregarImagenProducto(Integer idProducto, String urlImagen, String nombreArchivo, 
                                                Integer tamañoArchivo, String tipoArchivo, Boolean esPrincipal) {
        // Validar que el producto exista
        var producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado con ID: " + idProducto));

        // Si se va a establecer como principal, quitar el estado principal de las otras imágenes
        if (Boolean.TRUE.equals(esPrincipal)) {
            productoImagenRepository.findByProductoIdProductoAndEsPrincipalTrue(idProducto)
                    .ifPresent(imagen -> {
                        imagen.setEsPrincipal(false);
                        productoImagenRepository.save(imagen);
                    });
        }

        var imagen = ProductoImagen.builder()
                .producto(producto)
                .urlImagen(urlImagen)
                .nombreArchivo(nombreArchivo)
                .tamañoArchivo(tamañoArchivo)
                .tipoArchivo(tipoArchivo)
                .esPrincipal(Boolean.TRUE.equals(esPrincipal))
                .build();

        return productoImagenRepository.save(imagen);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoImagen obtenerImagenPorId(Integer idImagen) {
        return productoImagenRepository.findById(idImagen)
                .orElseThrow(() -> new NotFoundException("Imagen no encontrada con ID: " + idImagen));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoImagen> listarImagenesPorProducto(Integer idProducto) {
        // Validar que el producto exista
        if (!productoRepository.existsById(idProducto)) {
            throw new NotFoundException("Producto no encontrado con ID: " + idProducto);
        }
        
        return productoImagenRepository.findByProductoIdProducto(idProducto);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoImagen obtenerImagenPrincipalPorProducto(Integer idProducto) {
        // Validar que el producto exista
        if (!productoRepository.existsById(idProducto)) {
            throw new NotFoundException("Producto no encontrado con ID: " + idProducto);
        }
        
        return productoImagenRepository.findByProductoIdProductoAndEsPrincipalTrue(idProducto)
                .orElseThrow(() -> new NotFoundException("El producto no tiene imagen principal"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoImagen> listarImagenesPorProductoOrdenadas(Integer idProducto) {
        // Validar que el producto exista
        if (!productoRepository.existsById(idProducto)) {
            throw new NotFoundException("Producto no encontrado con ID: " + idProducto);
        }
        
        return productoImagenRepository.findByProductoIdProductoOrderByPrincipal(idProducto);
    }

    @Override
    public ProductoImagen actualizarImagen(Integer idImagen, String urlImagen, String nombreArchivo, 
                                          Integer tamañoArchivo, String tipoArchivo, Boolean esPrincipal) {
        var imagen = productoImagenRepository.findById(idImagen)
                .orElseThrow(() -> new NotFoundException("Imagen no encontrada con ID: " + idImagen));

        // Si se va a establecer como principal, quitar el estado principal de las otras imágenes del mismo producto
        if (Boolean.TRUE.equals(esPrincipal) && !Boolean.TRUE.equals(imagen.getEsPrincipal())) {
            productoImagenRepository.findByProductoIdProductoAndEsPrincipalTrue(imagen.getProducto().getIdProducto())
                    .ifPresent(img -> {
                        img.setEsPrincipal(false);
                        productoImagenRepository.save(img);
                    });
        }

        // Actualizar campos
        if (urlImagen != null) imagen.setUrlImagen(urlImagen);
        if (nombreArchivo != null) imagen.setNombreArchivo(nombreArchivo);
        if (tamañoArchivo != null) imagen.setTamañoArchivo(tamañoArchivo);
        if (tipoArchivo != null) imagen.setTipoArchivo(tipoArchivo);
        if (esPrincipal != null) imagen.setEsPrincipal(esPrincipal);

        return productoImagenRepository.save(imagen);
    }

    @Override
    public void eliminarImagen(Integer idImagen) {
        var imagen = productoImagenRepository.findById(idImagen)
                .orElseThrow(() -> new NotFoundException("Imagen no encontrada con ID: " + idImagen));

        // Si es la imagen principal, verificar que haya otras imágenes para establecer una nueva como principal
        if (Boolean.TRUE.equals(imagen.getEsPrincipal())) {
            List<ProductoImagen> otrasImagenes = productoImagenRepository.findByProductoIdProducto(
                    imagen.getProducto().getIdProducto());
            
            otrasImagenes.stream()
                    .filter(img -> !img.getIdImagen().equals(idImagen))
                    .findFirst()
                    .ifPresent(img -> {
                        img.setEsPrincipal(true);
                        productoImagenRepository.save(img);
                    });
        }

        productoImagenRepository.delete(imagen);
    }

    @Override
    public void eliminarImagenesPorProducto(Integer idProducto) {
        // Validar que el producto exista
        if (!productoRepository.existsById(idProducto)) {
            throw new NotFoundException("Producto no encontrado con ID: " + idProducto);
        }
        
        productoImagenRepository.deleteByProductoIdProducto(idProducto);
    }

    @Override
    public ProductoImagen establecerImagenPrincipal(Integer idImagen) {
        var imagen = productoImagenRepository.findById(idImagen)
                .orElseThrow(() -> new NotFoundException("Imagen no encontrada con ID: " + idImagen));

        // Quitar el estado principal de todas las imágenes del producto
        productoImagenRepository.findByProductoIdProductoAndEsPrincipalTrue(imagen.getProducto().getIdProducto())
                .ifPresent(img -> {
                    img.setEsPrincipal(false);
                    productoImagenRepository.save(img);
                });

        // Establecer esta imagen como principal
        imagen.setEsPrincipal(true);
        return productoImagenRepository.save(imagen);
    }
}