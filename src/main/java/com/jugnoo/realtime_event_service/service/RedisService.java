package com.jugnoo.realtime_event_service.service;

import com.jugnoo.realtime_event_service.model.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    private static final String ORDER_KEY_PREFIX = "order:";

    // Save an OrderEvent object with optional TTL
    public void saveOrder(OrderEvent event, long ttlSeconds) {
        String key = ORDER_KEY_PREFIX + event.getOrderId();
        redisTemplate.opsForValue().set(key, event, Duration.ofSeconds(ttlSeconds));
        log.info("Stored in Redis with TTL {}s: {}", ttlSeconds, event);
    }

    // Overload: save without TTL (default)
    public void saveOrder(OrderEvent event) {
        String key = ORDER_KEY_PREFIX + event.getOrderId();
        redisTemplate.opsForValue().set(key, event);
        log.info("Stored in Redis: {}", event);
    }

    // Retrieve an OrderEvent object
    public OrderEvent getOrder(String orderId) {
        String key = ORDER_KEY_PREFIX + orderId;
        Object obj = redisTemplate.opsForValue().get(key);
        if (obj != null) {
            return (OrderEvent) obj;
        }
        log.info("Order not found in Redis: {}", orderId);
        return null;
    }

    // Generic String key/value methods
    public void save(String key, String value, long ttlSeconds) {
        redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(ttlSeconds));
        log.info("Stored in Redis (String) with TTL {}s: {}={}", ttlSeconds, key, value);
    }

    public void save(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        log.info("Stored in Redis (String): {}={}", key, value);
    }

    public String get(String key) {
        Object obj = redisTemplate.opsForValue().get(key);
        return obj != null ? obj.toString() : null;
    }
}
