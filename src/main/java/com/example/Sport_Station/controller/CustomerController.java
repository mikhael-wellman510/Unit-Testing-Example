package com.example.Sport_Station.controller;

import com.example.Sport_Station.dto.request.OrderRequest;
import com.example.Sport_Station.service.impl.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CustomerController {

    private final CustomerServiceImpl customerServiceImpl;

    @PostMapping("/custOrder")
    public ResponseEntity<?>custOrder(@RequestBody OrderRequest orderRequest){

        var data = customerServiceImpl.createOrderCustomers(orderRequest);

        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/endCustOrder/{id}")
    public ResponseEntity<?>deletedCust(@PathVariable Long id){
        var data = customerServiceImpl.deleteCustomer(id);

        return ResponseEntity.ok(data);
    }
}
