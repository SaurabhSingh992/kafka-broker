package com.kafka.distributor.kafkaconfig;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AdminConfig {

    @Bean
    public KafkaAdmin admin()
    {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092");
        return new KafkaAdmin(configs);
    }


    @Bean
    public NewTopic topic1()
    {
        return TopicBuilder.name("test-topic")
                .partitions(1)
                .replicas(1)
                .build();
    }

}
