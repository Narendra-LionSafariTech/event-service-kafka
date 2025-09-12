package com.jugnoo.realtime_event_service.controller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
public class RedisTestController {

    private final StringRedisTemplate redisTemplate;

    public RedisTestController(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostMapping("/set/{key}/{value}")
    public String setValue(@PathVariable String key, @PathVariable String value) {
        redisTemplate.opsForValue().set(key, value);
        return "Key set: " + key + " -> " + value;
    }

    @GetMapping("/get/{key}")
    public String getValue(@PathVariable String key) {
        return redisTemplate.opsForValue().get(key);
    }
}