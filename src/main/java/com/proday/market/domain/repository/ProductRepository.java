package com.proday.market.domain.repository;

import com.proday.market.domain.Product;

import java.util.List;
import java.util.Optional;

/**
 * @author Ruben Malaga
 */
public interface ProductRepository {

    public List<Product> getAll();

    public Optional<List<Product>> getByCategory(int categoryId);

    public Optional<List<Product>> getScarseProducts(int quantity);

    public Optional<Product> getProduct(int productId);

    public Product save(Product product);

    public void delete(int productId);
}
