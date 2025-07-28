package com.example.Sport_Station.test;

import com.example.Sport_Station.dto.request.OrderDto;
import com.example.Sport_Station.dto.request.OrderRequest;
import com.example.Sport_Station.dto.response.CustomerServiceResponse;
import com.example.Sport_Station.dto.response.OrdersResponse;
import com.example.Sport_Station.entity.Customer;
import com.example.Sport_Station.entity.Orders;
import com.example.Sport_Station.repository.CustomerRepository;
import com.example.Sport_Station.service.impl.CustomerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@Slf4j
@SpringBootTest
public class CustomerServiceTestImpl {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    private CustomerServiceResponse customerServiceResponse;
    private OrderRequest orderRequest;
    private Customer customer;
    private Customer customerSave;

    @BeforeEach
    public void setup(){
        customer = Customer.builder()
                .id(1L)
                .name("Danny D")
                .email("danny@gmail.com")
                .orders(new ArrayList<>())
                .build();

        customerSave = Customer.builder()
                .id(1L)
                .name("Danny D")
                .email("danny@gmail.com")
                .orders(List.of(
                        Orders.builder()
                                .id(1L)
                                .itemName("Sabun")
                                .price(1000.0)
                                .orderDate(LocalDateTime.now())
                                .build() ,
                        Orders.builder()
                                .id(2L)
                                .itemName("Shampo")
                                .price(4000.0)
                                .orderDate(LocalDateTime.now())
                                .build()))
                .build();

        orderRequest = OrderRequest.builder()
                .customerId(1L)
                .orderList(
                        List.of(
                        OrderDto.builder()
                                .itemName("Sabun")
                                .price(1000.0)
                                .build(),
                        OrderDto.builder()
                                .itemName("Shampo")
                                .price(4000.0)
                                .build()))
                .build();
        customerServiceResponse = CustomerServiceResponse.builder()
                .id(customerSave.getId())
                .name(customerSave.getName())
                .email(customerSave.getEmail())
                .ordersList(customerSave.getOrders().stream().map(val -> OrdersResponse.builder()
                        .id(val.getId())
                        .itemName(val.getItemName())
                        .price(val.getPrice())
                        .orderDate(val.getOrderDate())
                        .build()).toList())
                .totalPrice(customerSave.getOrders().stream().mapToDouble(Orders::getPrice).sum())
                .build();
    }

    @Test
    public void createOrderCustomerTest(){
        Mockito.when(customerRepository.findById(orderRequest.getCustomerId())).thenReturn(Optional.of(customer));
        Mockito.when(customerRepository.save(any(Customer.class))).thenReturn(customerSave);

        CustomerServiceResponse res = customerServiceImpl.createOrderCustomers(orderRequest);

        log.info("Res  : {} " , res.getOrdersList());
        log.info("cust : {} " ,customerServiceResponse.getOrdersList());
        Assertions.assertNotNull(res);
        Assertions.assertEquals(1 ,res.getId());
        Assertions.assertEquals("Danny D" , res.getName());
        Assertions.assertEquals(5000, res.getTotalPrice());
        Assertions.assertEquals("Sabun" , res.getOrdersList().get(0).getItemName());
    }

    @Test
    public void deletedCustomerTest(){


        Mockito.when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        String res = customerServiceImpl.deleteCustomer(customer.getId());

        Assertions.assertEquals("Success Deleted : Danny D" , res);
    }




}
