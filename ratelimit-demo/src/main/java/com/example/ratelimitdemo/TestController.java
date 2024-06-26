package com.example.ratelimitdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final RateLimiterService rateLimiterService;

    public TestController(RateLimiterService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
    }

    @GetMapping("/test")
    public String test() {
        // 例如，对"/test"接口进行限流，10秒内最多允许5次请求
        boolean isAllowed = rateLimiterService.isAllowed("test", 5, 10);
        if (isAllowed) {
            return "Request processed.";
        } else {
            return "Too many requests. Please try again later.";
        }
    }
}
