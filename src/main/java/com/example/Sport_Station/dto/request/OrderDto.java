package com.example.Sport_Station.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class OrderDto {
    private String itemName;
    private Double price;
}
