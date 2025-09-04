package com.example.Sport_Station.service.impl;

import com.example.Sport_Station.config.RabbitMqConfig;
import com.example.Sport_Station.dto.response.ProfileRespons;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BroadcastServiceImpl {
    private final ObjectMapper mapper;

    @RabbitListener(queues = RabbitMqConfig.BROADCAST_QUEUE)
    public void getBroadcast(ProfileRespons profileRespons){

        log.info("ProfileRespons : {}" , profileRespons);

    }

    @RabbitListener(queues = RabbitMqConfig.BROADCAST_MESSAGE_QUEUE)
    public void getBroadcastMessage(String msg){
        log.info("Hasil msg : {} ", msg);
    }
}
