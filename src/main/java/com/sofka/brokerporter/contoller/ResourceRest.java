package com.sofka.brokerporter.contoller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/broker/porter")
public class ResourceRest {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping(value = "/correspondence")
    public String sendCorrespondence(@RequestParam("exchangeName") String exchange, @RequestParam("routingKey") String routingKey,
                                           @RequestParam("messageData") String messageData){

        amqpTemplate.convertAndSend(exchange, routingKey, messageData);

        return "Message sent to the RabbitMQ Topic Exchange Successfully";
    }

}
