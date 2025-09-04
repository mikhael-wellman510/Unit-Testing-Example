package com.example.Sport_Station.service.impl;

import com.example.Sport_Station.config.RabbitMqConfig;
import com.example.Sport_Station.entity.ProductRabbit;
import com.example.Sport_Station.repository.ProductRabitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductRabbitServiceImpl {

    private final ProductRabitRepository productRabitRepository;


    public void testRabbit(Long id){

        log.info("Cek {} " , id);

        ProductRabbit pr = productRabitRepository.findById(id).orElseThrow(()->new RuntimeException("Not found"));

        pr.setStock(pr.getStock()-1);

        productRabitRepository.save(pr);
    }
    @RabbitListener(queues = "payment.queue")
    public void testRabbit2(Long id){

        log.info("Cek 3{} " , id);

//        ProductRabbit pr = productRabitRepository.findById(id).orElseThrow(()->new RuntimeException("Not found"));
//
//        pr.setStock(pr.getStock()-1);
//
//        productRabitRepository.save(pr);
    }

}
