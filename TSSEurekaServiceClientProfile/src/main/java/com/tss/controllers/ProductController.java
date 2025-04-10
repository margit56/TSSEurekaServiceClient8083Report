package com.tss.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.tss.repositories.ProductRepository;
import com.tss.entities.Product;
import com.tss.to.ProductTO;
/**
 *
 * @author mariusz
 */
@RestController
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    ProductRepository productRepository;
    
    @GetMapping()
    public List<Product> list() {
        java.util.List<Product> productList = productRepository.findAll();
//        java.util.List<ProductTO> productToList = new java.util.ArrayList();
//        for(Product product: productList)
//        {
//            productToList
//                    .add(new ProductTO(product.getId(),product.getName(),product.getPrice(),product.getUpdated(),product.getDescription()));
//        }
//        return productToList;
          return productList;
    }
    
    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) {
        Product product = productRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" +id));
        return product;
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody Product input) {
        Product product = new Product();
        product.setId(id);
        product.setName(input.getName());
        product.setPrice(input.getPrice());
        product.setUpdated(input.getUpdated());
        product.setDescription(input.getDescription());
        productRepository.save(product);        
        return null;
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Product input) {
        Product product = new Product();
        product.setName(input.getName());
        product.setPrice(input.getPrice());
        product.setUpdated(input.getUpdated());
        product.setDescription(input.getDescription());
        productRepository.save(product);
        return null;
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Product product = productRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" +id));
        productRepository.delete(product);
        return null;
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }
}
