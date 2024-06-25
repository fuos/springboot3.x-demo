package com.example.redissondemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class DelayedQueueController {

    private final DelayedQueueService delayedQueueService;

    public DelayedQueueController(DelayedQueueService delayedQueueService) {
        this.delayedQueueService = delayedQueueService;
    }

    @GetMapping("/add")
    public String addToQueue(@RequestParam String item, @RequestParam long delay) {
        delayedQueueService.addToQueue(item, delay, TimeUnit.SECONDS);
        return "Item added to queue with delay of " + delay + " seconds";
    }

    @GetMapping("/take")
    public String takeFromQueue() {
        String item = delayedQueueService.takeFromQueue();
        return item != null ? "Item taken from queue: " + item : "Queue is empty";
    }
}
