package com.example.Sport_Station.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class ProductRequest {

    private Long id;
    private String name;
    private String slug;
    private String description;
    private String sku;
    private Double price;
    private Integer sold;
    private Boolean onSale;
}
