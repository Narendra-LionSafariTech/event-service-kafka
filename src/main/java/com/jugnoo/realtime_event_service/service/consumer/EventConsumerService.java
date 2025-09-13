package com.jugnoo.realtime_event_service.service.consumer;
import com.jugnoo.realtime_event_service.service.RedisService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jugnoo.realtime_event_service.model.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventConsumerService {

    private final RedisService redisService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "order-events", groupId = "order-group")
    public void consumeOrderEvent(String message) {
        try {
            OrderEvent orderEvent = objectMapper.readValue(message, OrderEvent.class);
            System.out.println("📩 Consumed Order Event: " + orderEvent);
            redisService.saveOrder(orderEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}