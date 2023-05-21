package com.kafka.distributor.service;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class KafkaMessageConsumer {


    @Value("${topic.name.consumer}")
    private String topicName;

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id")
    public void consume(ConsumerRecord<String, String> payload){
        log.info("Topic: {}"+ topicName);
        log.info("key: {}"+ payload.key());
        log.info("Headers: {}"+ payload.headers());
        log.info("Partion: {}"+ payload.partition());
        log.info("Order: {}"+payload.value());
    }
}
