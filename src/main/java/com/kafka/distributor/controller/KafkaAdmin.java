package com.kafka.distributor.controller;

import com.kafka.distributor.common.TopicConfig;
import com.kafka.distributor.service.KafkaAdminService;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaAdmin {

    @Autowired
    KafkaAdminService kafkaAdminService;

    @PostMapping("api/v1/admin/createtopic")
    public NewTopic creatTopic(@RequestBody TopicConfig topicConfig){
        return kafkaAdminService.createTopic(topicConfig.getTopicName(), topicConfig.getPartitionCount(), topicConfig.getReplicaCount());
    }
}
