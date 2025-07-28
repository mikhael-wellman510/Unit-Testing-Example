package com.example.Sport_Station.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "color")
@Builder(toBuilder = true)
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "color_name")
    private String colorName;
    @Column(name = "hex_code")
    private String hexCode;
}
