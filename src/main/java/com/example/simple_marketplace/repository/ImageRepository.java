package com.example.simple_marketplace.repository;

import com.example.simple_marketplace.modul.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository  extends JpaRepository<Image,Integer> {

    Optional<Image> findByIdAndDeletedAtIsNull(Integer id);
}
