package com.example.simple_marketplace.repository;

import com.example.simple_marketplace.modul.Loaner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanerRepository extends JpaRepository<Loaner,Integer> {

    @Query(
            value = "select * from loaner where  deleted_at is null",
            countQuery = "select * from loaner where deleted_at is null",
            nativeQuery = true
    )
    Page<Loaner> findAllByPage(Pageable pageable);

    Optional<Loaner> findByIdAndDeletedAtIsNull(Integer id);
}
