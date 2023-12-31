package com.example.simple_marketplace.repository;

import com.example.simple_marketplace.modul.Reports;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportsRepository extends JpaRepository<Reports,Integer> {


    @Query(
            value ="select * from reports where deleted_at is null",
            countQuery = "select * from reports where deleted_at is null",
            nativeQuery = true

    )
    Page<Reports> findAllByPage(Pageable pageable);


    Optional<Reports> findByIdAndDeletedAtIsNull(Integer id);
}
