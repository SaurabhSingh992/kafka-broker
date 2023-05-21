package com.kafka.distributor.common;

public class TopicConfig {

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Integer getPartitionCount() {
        return partitionCount;
    }

    public void setPartitionCount(Integer partitionCount) {
        this.partitionCount = partitionCount;
    }




    private String topicName;
    private Integer partitionCount;

    public Short getReplicaCount() {
        return replicaCount;
    }

    public void setReplicaCount(Short replicaCount) {
        this.replicaCount = replicaCount;
    }

    private Short replicaCount;
}
