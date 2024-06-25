package com.example.redissondemo;

import org.redisson.api.RDelayedQueue;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Service
public class DelayedQueueService {

    private final RedissonClient redissonClient;

    public DelayedQueueService(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    private RDelayedQueue<String> delayedQueue;

    @PostConstruct
    public void init() {
        RQueue<String> queue = redissonClient.getQueue("myQueue");
        delayedQueue = redissonClient.getDelayedQueue(queue);
    }

    public void addToQueue(String item, long delay, TimeUnit timeUnit) {
        delayedQueue.offer(item, delay, timeUnit);
    }

    public String takeFromQueue() {
        RQueue<String> queue = redissonClient.getQueue("myQueue");
        return queue.poll();
    }
}
