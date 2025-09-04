package com.example.Sport_Station.repository;

import com.example.Sport_Station.entity.ProductRabbit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRabitRepository extends JpaRepository<ProductRabbit,Long> {
}
