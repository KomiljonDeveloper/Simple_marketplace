package com.example.simple_marketplace.repository;

import com.example.simple_marketplace.modul.Employees;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees,Integer> {

    @Query(
            value = "select * from employees where deleted_at is null",
            countQuery = "select * from employees where deleted_at is null",
            nativeQuery = true
    )
    Page<Employees> findAllByPage(Pageable pageable);


Optional<Employees> findByIdAndDeletedAtIsNull(Integer id);
}
