package com.ivinicius.pollservice.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    private String RESULTS_TOPIC = "poll.results";

    @Bean
    public TopicExchange resultsExchange() {
        return new TopicExchange(RESULTS_TOPIC);
    }

}
