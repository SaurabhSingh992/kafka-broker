package com.kafka.distributor.service;

import com.kafka.distributor.common.TopicConfig;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KafkaAdminService {

    private final ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory;


    public KafkaAdminService(ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory){
        this.concurrentKafkaListenerContainerFactory= concurrentKafkaListenerContainerFactory;
    }

    public NewTopic createTopic(String name, Integer partition, Integer replicas){
        NewTopic newTopic= TopicBuilder.name(name)
                .partitions(partition)
                .replicas(replicas)
                .build();
        return newTopic;
    }

    public List<TopicConfig>  listTopic(){
        List<TopicConfig> topicList= new ArrayList<>();
        Map<String, List<PartitionInfo>> topics= new HashMap<>();
        Map<String, Object> configmap= concurrentKafkaListenerContainerFactory.getConsumerFactory().getConfigurationProperties();
        KafkaConsumer consumer = new KafkaConsumer<String, String>(configmap);
        topics = consumer.listTopics();
        consumer.close();
        for(String topicName: topics.keySet()){
            TopicConfig topicConfig= new TopicConfig();
            topicConfig.setTopicName(topicName);
            topicConfig.setPartitionCount(topics.get(topicName).size());
            topicConfig.setReplicaCount(topics.get(topicName).get(0).replicas().length);
            topicList.add(topicConfig);
        }
        return topicList;
    }

}
