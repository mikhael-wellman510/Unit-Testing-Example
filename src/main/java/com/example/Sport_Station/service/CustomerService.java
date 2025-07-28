package com.example.Sport_Station.service;

import com.example.Sport_Station.dto.request.OrderRequest;
import com.example.Sport_Station.dto.response.CustomerServiceResponse;

public interface CustomerService {
    CustomerServiceResponse createOrderCustomers(OrderRequest orderRequest);
}
