package com.kafka.distributor.service;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class KafkaAdminService {

    public NewTopic createTopic(String name, Integer partition, Integer replicas){
       return TopicBuilder.name(name)
                .partitions(partition)
                .replicas(replicas)
                .build();
    }

}
