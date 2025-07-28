package com.example.Sport_Station.dto.request;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderRequest {
    private Long customerId;
    private List<OrderDto>orderList;
}
