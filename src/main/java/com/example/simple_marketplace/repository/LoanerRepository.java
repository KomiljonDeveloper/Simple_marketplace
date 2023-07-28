package com.example.simple_marketplace.repository;

import com.example.simple_marketplace.modul.Loaner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanerRepository extends JpaRepository<Loaner,Integer> {
    Optional<Loaner> findByIdAndDeletedAtIsNull(Integer id);
}
