package com.example.simple_marketplace.repository;

import com.example.simple_marketplace.modul.ForeignDebt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ForeignDebtRepository extends JpaRepository<ForeignDebt,Integer> {

    @Query(
            value = "select * from foreign_debt where deleted_at is null",
            countQuery = "select * from foreign_debt where deleted_at is null",
            nativeQuery = true
    )
    Page<ForeignDebt> findAllByPage(Pageable pageable);



    Optional<ForeignDebt> findByIdAndDeletedAtIsNull(Integer id);
}
