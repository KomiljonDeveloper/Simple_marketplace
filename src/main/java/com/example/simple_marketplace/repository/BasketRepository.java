package com.example.simple_marketplace.repository;

import com.example.simple_marketplace.modul.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket,Integer> {
    Optional<Basket> findByIdAndDeletedAtIsNull(Integer id);
}
