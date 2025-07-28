package com.example.Sport_Station.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
@Builder(toBuilder = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private String slug;
    private String description;
    private String sku;
    private Double price;
    private Integer sold; // -> sudah berapa banyak yang terjual

    @Column(name = "on_sale")
    private Boolean onSale; // -> lagi promo atau tidak
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
