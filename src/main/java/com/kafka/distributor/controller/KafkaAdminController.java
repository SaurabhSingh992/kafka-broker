package com.kafka.distributor.controller;

import com.kafka.distributor.common.TopicConfig;
import com.kafka.distributor.service.KafkaAdminService;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KafkaAdminController {

    @Autowired
    KafkaAdminService kafkaAdminService;

    @PostMapping(value="api/v1/admin/createtopic",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String creatTopic(@RequestBody TopicConfig topicConfig){
        return kafkaAdminService.createTopic(topicConfig.getTopicName(), topicConfig.getPartitionCount(), topicConfig.getReplicaCount(), null);
    }

    @GetMapping("api/v1/admin/listTopic")
    public List<TopicConfig> listTopic(){
        return kafkaAdminService.listTopic();
    }

}
