package com.ivinicius.pollservice.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class RabbitProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publish(String message, String topic){
        Mono.fromCallable(() -> {
            rabbitTemplate.convertAndSend(topic, "" , message);
            return null;
        }).subscribe();
    }
}
