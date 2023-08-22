package com.example.simple_marketplace.repository;

import com.example.simple_marketplace.dto.BasketDto;
import com.example.simple_marketplace.modul.Basket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket,Integer> {

    @Query(
            value = "select * from basket where deleted_at is null order by id \n-- #pageable\n",
            countQuery = "select * from basket where deleted_at is null",
            nativeQuery = true
    )
    Page<Basket> findAllByPage(Pageable pageable);


    Optional<Basket> findByIdAndDeletedAtIsNull(Integer id);
}
