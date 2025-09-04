package com.example.Sport_Station.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_rabbit")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductRabbit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer stock;
}
