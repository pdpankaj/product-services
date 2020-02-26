package com.java.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.products.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}