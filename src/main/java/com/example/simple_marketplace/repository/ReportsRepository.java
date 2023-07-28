package com.example.simple_marketplace.repository;

import com.example.simple_marketplace.modul.Reports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportsRepository extends JpaRepository<Reports,Integer> {
    Optional<Reports> findByIdAndDeletedAtIsNull(Integer id);
}
