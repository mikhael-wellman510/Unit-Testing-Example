package com.example.Sport_Station.dto.response;

import com.example.Sport_Station.entity.Orders;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class CustomerServiceResponse {
    private Long id;
    private String name;
    private String email;
    private List<OrdersResponse>ordersList;
    private Double totalPrice;

}
