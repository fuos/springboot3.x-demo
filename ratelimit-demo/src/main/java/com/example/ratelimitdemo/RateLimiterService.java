package com.example.ratelimitdemo;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RateLimiterService {

    private final RedisTemplate<String, Long> redisTemplate;

    public RateLimiterService(RedisTemplate<String, Long> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean isAllowed(String key, long maxRequest, long windowInSeconds) {
        long currentTimeMillis = System.currentTimeMillis();
        long windowStartMillis = currentTimeMillis - windowInSeconds * 1000;
        String redisKey = "rate_limiter:" + key;

        // 清除窗口开始前的请求记录
        redisTemplate.opsForZSet().removeRangeByScore(redisKey, 0, windowStartMillis);

        // 获取当前窗口内的请求次数
        Long currentCount = redisTemplate.opsForZSet().count(redisKey, windowStartMillis, currentTimeMillis);

        if (currentCount != null && currentCount >= maxRequest) {
            // 如果当前窗口内的请求已达到上限，则不允许请求
            return false;
        } else {
            // 记录当前请求
            redisTemplate.opsForZSet().add(redisKey, currentTimeMillis, currentTimeMillis);
            // 设置过期时间，避免无限增长
            redisTemplate.expire(redisKey, Duration.ofSeconds(windowInSeconds + 1));
            return true;
        }
    }
}
