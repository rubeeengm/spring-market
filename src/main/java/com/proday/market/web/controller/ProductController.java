package com.proday.market.web.controller;

import com.proday.market.domain.Product;
import com.proday.market.domain.service.ProductService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ruben Malaga
 */
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    @ApiOperation("Get all supermarket products")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(
            this.productService.getAll(), HttpStatus.OK
        );
    }

    @GetMapping("/{productId}")
    @ApiOperation("Search a product with an ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK")
        , @ApiResponse(code = 404, message = "Product not found")
    })
    public ResponseEntity<Product> getProduct(
        @ApiParam(value = "The id of the product", required = true, example = "7")
        @PathVariable("productId") int productId
    ) {
        return this.productService.getProduct(productId)
                    .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND))
        ;
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getByCategory(
        @PathVariable("categoryId") int categoryId
    ) {
        return this.productService.getByCategory(categoryId)
                    .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND))
        ;
    }

    @PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return new ResponseEntity<>(
            this.productService.save(product), HttpStatus.CREATED
        );
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity delete(@PathVariable("productId") int productId) {
        if (this.productService.delete(productId)) {
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
