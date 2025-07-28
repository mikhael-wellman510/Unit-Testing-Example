package com.example.Sport_Station.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

    @Entity
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder(toBuilder = true)
    @Table(name = "orders")
    public class Orders {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "item_name")
        private String itemName;

        private Double price;

        @Column(name = "order_date")
        private LocalDateTime orderDate;

        @ManyToOne
        @JoinColumn(name = "customer_id")
        private Customer customer;
    }
