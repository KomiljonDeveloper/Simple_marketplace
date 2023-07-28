package com.example.simple_marketplace.repository;

import com.example.simple_marketplace.modul.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees,Integer> {
Optional<Employees> findByIdAndDeletedAtIsNull(Integer id);
}
