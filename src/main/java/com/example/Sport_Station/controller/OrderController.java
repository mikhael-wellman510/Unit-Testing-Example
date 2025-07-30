package com.example.Sport_Station.controller;

import com.example.Sport_Station.service.impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class OrderController {

    private final OrderServiceImpl orderService;

    @GetMapping("/searchOrder")
    public ResponseEntity<?>searchOrder(@RequestParam(name = "page" , defaultValue = "0")Integer page , @RequestParam(name = "size" , defaultValue = "5")Integer size , @RequestParam(name = "customerName" , defaultValue = "")String customerName , @RequestParam(name = "itemName" , defaultValue = "")String itemName){

        var res = orderService.orderRes(page, size, customerName, itemName);
        log.info("Hasil res :{} " , res);
        return ResponseEntity.ok(res);
    }
}
