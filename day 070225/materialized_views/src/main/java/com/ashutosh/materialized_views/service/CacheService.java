package com.ashutosh.materialized_views.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
@Service
@Slf4j
public class CacheService {
    private final RedisTemplate<String, Object> redisTemplate;

    public CacheService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void put(String key, Object value, String cacheName) {
        String fullKey = cacheName + "::" + key;
        redisTemplate.opsForValue().set(fullKey, value, 30, TimeUnit.MINUTES);  // Increased to 30 minutes
    }

    public Object get(String key, String cacheName) {
        String fullKey = cacheName + "::" + key;
        return redisTemplate.opsForValue().get(fullKey);
    }

    public boolean hasKey(String key, String cacheName) {
        String fullKey = cacheName + "::" + key;
        Boolean hasKey = redisTemplate.hasKey(fullKey);
        log.debug("Checking cache key: {}, exists: {}", fullKey, hasKey);
        return Boolean.TRUE.equals(hasKey);
    }

    public void invalidate(String key) {
        redisTemplate.delete(key);
    }

    public void invalidateAll() {
        redisTemplate.keys("randomData::*").forEach(this::invalidate);
    }
}