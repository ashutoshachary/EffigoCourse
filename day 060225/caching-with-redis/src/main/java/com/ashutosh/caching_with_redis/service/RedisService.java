package com.ashutosh.caching_with_redis.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void clearAllCache() {
        redisTemplate.getConnectionFactory().getConnection().flushAll();
    }
}
