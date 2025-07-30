package com.example.Sport_Station.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderDto {
    private Long id;
    private String itemName;
    private String orderDate;
    private Double price;
    private Long customerId;
    private String email;
    private String name;
}
