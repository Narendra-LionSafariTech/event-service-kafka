package com.jugnoo.realtime_event_service.service;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "realtime-topic", groupId = "realtime-group")
    public void listen(String message) {
        System.out.println("📩 Received from Kafka: " + message);
    }
}
