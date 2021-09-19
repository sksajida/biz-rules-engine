package com.tuplescale.graph.kafka;

import java.util.LinkedHashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.tuplescale.graph.model.AffectedTSCalcNode;

@Component
public class KafkaPublisher {

    private static Logger logger = LoggerFactory.getLogger(KafkaPublisher.class);

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String bs, String topicName) {
        logger.info("Publishing message: {} into topic: {}", bs, topicName);
        kafkaTemplate.send(topicName, bs);
    }
}
