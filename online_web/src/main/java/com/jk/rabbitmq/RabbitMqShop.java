package com.jk.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqShop {

    @Bean
    public Queue messageQueue() {
        return new Queue("user-register");
    }

    @Bean
    public Queue catQueue() {
        return new Queue("add-Cart");
    }
}
