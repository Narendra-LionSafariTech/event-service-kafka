package com.jugnoo.realtime_event_service.controller;

import com.jugnoo.realtime_event_service.model.OrderEvent;
import com.jugnoo.realtime_event_service.service.producer.EventProducerService;
import com.jugnoo.realtime_event_service.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final EventProducerService producerService;
    private final RedisService redisService;

    /**
     * Create a new order event -> send to Kafka
     */
    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderEvent orderEvent) {
        producerService.sendOrderEvent(orderEvent);
        return ResponseEntity.ok("✅ Order event sent to Kafka: " + orderEvent.getOrderId());
    }

    /**
     * Get latest order status from Redis
     */
    @GetMapping("/{orderId}/status")
    public ResponseEntity<?> getOrderStatus(@PathVariable String orderId) {
        OrderEvent orderEvent = redisService.getOrder(orderId);
        if (orderEvent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderEvent);
    }
}
