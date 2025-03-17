package com.msa.fiveio.order.infrastructure.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderQueueConfig {

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Value("${message.orderToDelivery.exchange}")
    private String exchange;

    @Value("${message.orderToDelivery.queue.delivery}")
    private String queueDelivery;

    @Value("${message.delivery.queue.created.completed}")
    private String queueDeliveryCreatedCompleted;

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Queue queueDelivery() {
        return new Queue(queueDelivery);
    }

    @Bean
    public Queue queueDeliveryCreatedCompleted() {
        return new Queue(queueDeliveryCreatedCompleted);
    }

    @Bean
    public Binding bindingDelivery() {
        return BindingBuilder.bind(queueDelivery()).to(exchange()).with(queueDelivery);
    }

    @Bean
    public Binding bindingDeliveryCreatedCompleted() {
        return BindingBuilder.bind(queueDeliveryCreatedCompleted()).to(exchange())
            .with(queueDeliveryCreatedCompleted);
    }

}
