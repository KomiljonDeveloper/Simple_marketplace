package com.example.simple_marketplace.repository;

import com.example.simple_marketplace.modul.ForeignDebt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ForeignDebtRepository extends JpaRepository<ForeignDebt,Integer> {
    Optional<ForeignDebt> findByIdAndDeletedAtIsNull(Integer id);
}
