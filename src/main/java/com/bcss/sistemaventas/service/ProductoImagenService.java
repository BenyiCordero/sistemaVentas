package com.bcss.sistemaventas.service;

import com.bcss.sistemaventas.domain.ProductoImagen;

import java.util.List;

public interface ProductoImagenService {
    
    ProductoImagen agregarImagenProducto(Integer idProducto, String urlImagen, String nombreArchivo, 
                                         Integer tamañoArchivo, String tipoArchivo, Boolean esPrincipal);
    
    ProductoImagen obtenerImagenPorId(Integer idImagen);
    
    List<ProductoImagen> listarImagenesPorProducto(Integer idProducto);
    
    ProductoImagen obtenerImagenPrincipalPorProducto(Integer idProducto);
    
    List<ProductoImagen> listarImagenesPorProductoOrdenadas(Integer idProducto);
    
    ProductoImagen actualizarImagen(Integer idImagen, String urlImagen, String nombreArchivo, 
                                   Integer tamañoArchivo, String tipoArchivo, Boolean esPrincipal);
    
    void eliminarImagen(Integer idImagen);
    
    void eliminarImagenesPorProducto(Integer idProducto);
    
    ProductoImagen establecerImagenPrincipal(Integer idImagen);
}