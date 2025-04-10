/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.tss.repositories;

import org.springframework.data.repository.CrudRepository;
import com.tss.entities.Product;


public interface ProductRepository extends CrudRepository<Product, Long> {
    
    @Override
    java.util.List<Product> findAll();
}
