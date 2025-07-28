package com.example.Sport_Station.repository;

import com.example.Sport_Station.entity.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<Color,Long> , JpaSpecificationExecutor<Color> {

    Optional<Color>findByColorName(String name);
    Page<Color>findByColorNameContainingIgnoreCase(String colorName, Pageable pageable);
}
