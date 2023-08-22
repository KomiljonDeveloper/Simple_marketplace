package com.example.simple_marketplace.repository;

import com.example.simple_marketplace.modul.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query(
            value = "select * from product where deleted_at is null",
            countQuery = "select * from product where deleted_at is null",
           nativeQuery = true
    )
    Page<Product> findAllByPage(Pageable pageable);


    Optional<Product> findByProductIdAndDeletedAtIsNull(Integer id);
}
