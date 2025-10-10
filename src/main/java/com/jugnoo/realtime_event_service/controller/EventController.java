package com.jugnoo.realtime_event_service.controller;

import com.jugnoo.realtime_event_service.model.OrderEvent;
import com.jugnoo.realtime_event_service.service.RedisService;
import com.jugnoo.realtime_event_service.service.producer.EventProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final RedisService redisService;
    private final EventProducerService EventProducerService;

    // Save OrderEvent in Redis
    @PostMapping("/cache")
    public String cacheEvent(@RequestBody OrderEvent orderEvent) {
        redisService.saveOrder(orderEvent);
        return "OrderEvent cached with ID: " + orderEvent.getOrderId();
    }

    // Get OrderEvent from Redis
    @GetMapping("/cache/{orderId}")
    public OrderEvent getCachedEvent(@PathVariable String orderId) {
        return redisService.getOrder(orderId);
    }

    // Publish message to Kafka
    @PostMapping("/publish")
    public String publishEvent(@RequestBody OrderEvent orderEvent) {
        EventProducerService.sendOrderEvent(orderEvent);
        return "Event published to Kafka";
    }

}
