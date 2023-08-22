package com.example.simple_marketplace.repository;

import com.example.simple_marketplace.dto.CategoryDto;
import com.example.simple_marketplace.modul.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Page<Category> findAllByDeletedAtIsNull(Pageable pageable);

    Optional<Category> findByCategoryIdAndDeletedAtIsNull(Integer id);
}
