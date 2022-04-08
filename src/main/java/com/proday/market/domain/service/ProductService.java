package com.proday.market.domain.service;

import com.proday.market.domain.Product;
import com.proday.market.domain.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Ruben Malaga
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return this.productRepository.getAll();
    }

    public Optional<Product> getProduct(int productId) {
        return this.productRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId) {
        return this.productRepository.getByCategory(categoryId);
    }

    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    public boolean delete(int productId) {
//        FORMA 1
//        if (this.getProduct(productId).isPresent()) {
//            this.productRepository.delete(productId);
//            return true;
//        }
//
//        return false;

//      FORMA 2
        return this.getProduct(productId).map(
            product -> {
                this.productRepository.delete(productId);

                return true;
            }
        ).orElse(false);
    }
}
