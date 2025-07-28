package com.example.Sport_Station.service.impl;

import com.example.Sport_Station.dto.request.OrderDto;
import com.example.Sport_Station.dto.request.OrderRequest;
import com.example.Sport_Station.dto.response.CustomerServiceResponse;
import com.example.Sport_Station.dto.response.OrdersResponse;
import com.example.Sport_Station.entity.Customer;
import com.example.Sport_Station.entity.Orders;
import com.example.Sport_Station.repository.CustomerRepository;
import com.example.Sport_Station.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;


    // Todo -> Pakai cascade all , jadi entity induk nya auto ke isi
    @Override
    @Transactional
    public CustomerServiceResponse createOrderCustomers(OrderRequest orderRequest) {

        try {
            // todo , find customer
            Customer customer = customerRepository.findById(orderRequest.getCustomerId()).orElseThrow(()-> new RuntimeException("Tidak Di Temukan ID nya"));

            List<Orders> ordersList = new ArrayList<>();

            for (OrderDto od : orderRequest.getOrderList()){

                Orders res = Orders.builder()
                        .itemName(od.getItemName())
                        .price(od.getPrice())
                        .orderDate(LocalDateTime.now())
                        .customer(customer) // Todo -> Wajib di tambahkan supaya masuk ke manyToOne
                        .build();
                ordersList.add(res);
            }
            // todo , save new order
            // Todo -> ini auto isi tabel orders , jadi ga perlu save satu2
            // Todo -> get dulu order , lalu addAll
            customer.getOrders().addAll(ordersList);
            Customer saveCustomer = customerRepository.save(customer);

            List<Orders> savedOrders = saveCustomer.getOrders();

            // Todo -> supaya ga kebawa semua order list dari one To many nya
            List<Orders> lastOrders = savedOrders.subList(savedOrders.size() - ordersList.size(), savedOrders.size());

            // todo mapping ke DTO
            List<OrdersResponse> order = new ArrayList<>();
            double totalPrice = 0;
            for (Orders or : lastOrders){
                order.add(OrdersResponse.builder()
                                .id(or.getId())
                                .itemName(or.getItemName())
                                .price(or.getPrice())
                                .orderDate(or.getOrderDate())
                        .build());
                totalPrice = totalPrice + or.getPrice();
            }


            return CustomerServiceResponse.builder()
                    .id(saveCustomer.getId())
                    .name(saveCustomer.getName())
                    .email(saveCustomer.getEmail())
                    .ordersList(order)
                    .totalPrice(totalPrice)
                    .build();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }
}
