package com.proday.market.persistence;

import com.proday.market.persistence.crud.ProductoCrudRepository;
import com.proday.market.persistence.entity.Producto;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Ruben Malaga
 */
@Repository
public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;

    public List<Producto> getAll() {
        return (List<Producto>) productoCrudRepository.findAll();
    }

    public List<Producto> getByCategoria(int idCategoria) {
        return this.productoCrudRepository.findByIdCategoriaOrderByNombreAsc(
            idCategoria
        );
    }

    public Optional<List<Producto>> getEscasos(int cantidad) {
        return this.productoCrudRepository.findByCantidadStockLessThanAndEstado(
            cantidad, true
        );
    }

    public Optional<Producto> getProducto(int idProducto) {
        return this.productoCrudRepository.findById(idProducto);
    }

    public Producto save(Producto producto) {
        return this.productoCrudRepository.save(producto);
    }

    public void delete(int idProducto) {
        this.productoCrudRepository.deleteById(idProducto);
    }
}
