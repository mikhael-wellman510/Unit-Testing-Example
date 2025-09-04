package com.example.Sport_Station.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RabbitMqConfig {

    public static final String BROADCAST_QUEUE = "broadcast.queue";
    public static final String BROADCAST_MESSAGE_QUEUE = "broadcast.message.queue";

    // Todo -> Dapatkan Pesan dari Produser
    @Bean
    public Queue broadcastQueue(){
        return new Queue(BROADCAST_QUEUE,true);
    }

    // Todo -> dapatkan pesan dari produser
    @Bean
    public Queue broadcastMessageQueue(){
        return new Queue(BROADCAST_MESSAGE_QUEUE,true);
    }


    // Todo-> Gunakan untuk convert ke json
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // Todo -> RabbitTemplate dengan converter
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jackson2JsonMessageConverter());
        return template;
    }
}
