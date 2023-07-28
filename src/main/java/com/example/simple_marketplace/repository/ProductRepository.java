package com.example.simple_marketplace.repository;

import com.example.simple_marketplace.modul.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Optional<Product> findByProductIdAndDeletedAtIsNull(Integer id);
}
