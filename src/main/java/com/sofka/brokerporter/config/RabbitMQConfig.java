package com.sofka.brokerporter.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    Queue floorOne() {
        return new Queue("floorOne", false);
    }

    @Bean
    Queue floorTwo() {
        return new Queue("floorTwo", false);
    }

    @Bean
    Queue floorThree() {
        return new Queue("floorThree", false);
    }

    @Bean
    Queue floorFour() {
        return new Queue("floorFour", false);
    }

    @Bean
    Queue floorFive() {
        return new Queue("floorFive", false);
    }

    @Bean
    Queue allQueue() {
        return new Queue("allQueue", false);
    }

    @Bean
    Queue pairQueue() {
        return new Queue("pairQueue", false);
    }

    @Bean
    Queue oddQueue() {
        return new Queue("oddQueue", false);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topic-exchange");
    }

    @Bean
    Binding floorOneBinding(Queue floorOne, TopicExchange topicExchange) {
        return BindingBuilder.bind(floorOne).to(topicExchange).with("queue.odd.floorOne");
    }

    @Bean
    Binding floorTwoBinding(Queue floorTwo, TopicExchange topicExchange) {
        return BindingBuilder.bind(floorTwo).to(topicExchange).with("queue.pair.floorTwo");
    }

    @Bean
    Binding floorThreeBinding(Queue floorThree, TopicExchange topicExchange) {
        return BindingBuilder.bind(floorThree).to(topicExchange).with("queue.odd.floorThree");
    }

    @Bean
    Binding floorFourBinding(Queue floorFour, TopicExchange topicExchange) {
        return BindingBuilder.bind(floorFour).to(topicExchange).with("queue.pair.floorFour");
    }

    @Bean
    Binding floorFiveBinding(Queue floorFive, TopicExchange topicExchange) {
        return BindingBuilder.bind(floorFive).to(topicExchange).with("queue.odd.floorFive");
    }

    @Bean
    Binding allBinding(Queue allQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(allQueue).to(topicExchange).with("queue.*");
    }

    @Bean
    Binding pairNumberBinding(Queue pairQueue, TopicExchange topicExchange) {
         return BindingBuilder.bind(pairQueue).to(topicExchange).with("queue.pair.*");
    }

     @Bean
    Binding oddNumberBinding(Queue oddQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(oddQueue).to(topicExchange).with("queue.odd.*");
    }

    @Bean
    MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        return simpleMessageListenerContainer;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
