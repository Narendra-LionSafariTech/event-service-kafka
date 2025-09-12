package com.jugnoo.realtime_event_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    public void save(String key, String value) {
        redisTemplate.opsForValue().set(key, value, Duration.ofMinutes(5));
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}