package com.optimiztiondb.news.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CacheConfig {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @PostConstruct
    public void clearCache() {
        redisTemplate.delete(Objects.requireNonNull(redisTemplate.keys("*")));
    }
}
