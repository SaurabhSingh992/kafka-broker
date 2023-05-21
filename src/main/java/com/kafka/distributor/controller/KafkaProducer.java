package com.kafka.distributor.controller;


import com.kafka.distributor.service.TopicProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaProducer {

    @Autowired
    TopicProducer topicProducer;

    @PostMapping(value = "/send")
    public void send(@RequestBody String message){
        topicProducer.send(message);
    }

}
