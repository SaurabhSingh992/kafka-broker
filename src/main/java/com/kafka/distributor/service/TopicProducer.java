package com.kafka.distributor.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Log4j2
public class TopicProducer {


    @Value("${topic.name.consumer}")
    private String topicName;

    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;


    public void send(String message){
       kafkaTemplate.send(topicName, message);
       log.info("Successfully sent message on topic : %s  and payload : %s ",  topicName, message);
    }

}
