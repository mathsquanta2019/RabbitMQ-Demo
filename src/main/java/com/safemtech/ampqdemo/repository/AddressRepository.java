package com.safemtech.ampqdemo.repository;

import com.safemtech.ampqdemo.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;

public interface AddressRepository extends JpaRepository<Address, Integer>, ListCrudRepository<Address, Integer> {
}
