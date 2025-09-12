package com.jugnoo.realtime_event_service.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaTestController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaTestController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/publish/{message}")
    public String sendMessage(@PathVariable String message) {
        kafkaTemplate.send("test-topic", message);
        return "Message sent to Kafka topic: " + message;
    }
}