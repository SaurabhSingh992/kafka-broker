package com.kafka.distributor.service;

import com.kafka.distributor.common.TopicConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KafkaAdminService {

    private final ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory;

    @Autowired
    KafkaAdmin kafkaAdmin;


    public KafkaAdminService(ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory){
        this.concurrentKafkaListenerContainerFactory= concurrentKafkaListenerContainerFactory;
    }

    public String createTopic(String name, Integer partition, Short replicas, Map<String, String> topicconfig){
        Map<String, Object> configmap= concurrentKafkaListenerContainerFactory.getConsumerFactory().getConfigurationProperties();
        NewTopic topic=new NewTopic(name,partition,replicas);
        if(topicconfig != null){
            topic.configs(topicconfig);
        }
        kafkaAdmin.createOrModifyTopics(topic);
        return "topic created"+ name;
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
            topicConfig.setReplicaCount((short) topics.get(topicName).get(0).replicas().length);
            topicList.add(topicConfig);
        }
        return topicList;
    }

}
