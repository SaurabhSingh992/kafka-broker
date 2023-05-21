package com.kafka.distributor.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TopicProducer {


    @Value("${topic.name.consumer")
    private String topicName;

    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;


    public void send(String message){
        System.out.println("Payload:"+  message);
       kafkaTemplate.send(topicName, message);
    }

}
