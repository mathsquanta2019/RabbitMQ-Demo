package com.safemtech.ampqdemo.repository;

import com.safemtech.ampqdemo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductRepository extends JpaRepository<Product, Integer>, ListCrudRepository<Product, Integer> {
}
