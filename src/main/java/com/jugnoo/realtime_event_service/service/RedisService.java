package com.jugnoo.realtime_event_service.service;

import com.jugnoo.realtime_event_service.model.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    // Save an OrderEvent object
    public void saveOrder(OrderEvent event) {
        redisTemplate.opsForValue().set("order:" + event.getOrderId(), event);
        System.out.println("💾 Stored in Redis: " + event);
    }

    // Retrieve an OrderEvent object
    public OrderEvent getOrder(String orderId) {
        return (OrderEvent) redisTemplate.opsForValue().get("order:" + orderId);
    }

    // Optional: generic String key/value methods
    public void save(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String get(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }
}
