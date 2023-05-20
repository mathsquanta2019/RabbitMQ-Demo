package com.safemtech.ampqdemo.repository;


import com.safemtech.ampqdemo.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;

public interface OrderRepository extends JpaRepository<Orders, String>, ListCrudRepository<Orders, String> {
}
