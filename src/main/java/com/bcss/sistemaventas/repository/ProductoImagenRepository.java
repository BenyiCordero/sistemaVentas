package com.bcss.sistemaventas.repository;

import com.bcss.sistemaventas.domain.ProductoImagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoImagenRepository extends JpaRepository<ProductoImagen, Integer> {
    
    List<ProductoImagen> findByProductoIdProducto(Integer idProducto);
    
    Optional<ProductoImagen> findByProductoIdProductoAndEsPrincipalTrue(Integer idProducto);
    
    @Query("SELECT pi FROM ProductoImagen pi WHERE pi.producto.idProducto = :idProducto ORDER BY pi.esPrincipal DESC, pi.createdAt ASC")
    List<ProductoImagen> findByProductoIdProductoOrderByPrincipal(Integer idProducto);
    
    void deleteByProductoIdProducto(Integer idProducto);
    
    boolean existsByProductoIdProductoAndEsPrincipalTrue(Integer idProducto);
    
    @Query("SELECT COUNT(pi) > 0 FROM ProductoImagen pi WHERE pi.producto.idProducto = :idProducto")
    boolean existsByProductoIdProducto(@Param("idProducto") Integer idProducto);
}