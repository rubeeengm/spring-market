package com.proday.market.persistence.crud;

import com.proday.market.persistence.entity.Producto;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Ruben Malaga
 */
public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

//    @Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true)
    public List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    public Optional<List<Producto>> findByCantidadStockLessThanAndEstado(
        int cantidadStock, boolean estado
    );
}
