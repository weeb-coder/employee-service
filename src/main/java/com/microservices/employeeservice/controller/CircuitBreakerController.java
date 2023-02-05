package com.microservices.employeeservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    @GetMapping("/sample-api")
//    @Retry(name = "sample-api", fallbackMethod = "fallbackResponse")
    @CircuitBreaker(name = "sample-api", fallbackMethod = "fallbackResponse")
    public String sampleApi() {
        return new RestTemplate().getForEntity("http://localhost:8080/some-endpoint", String.class).getBody();
    }

    public String fallbackResponse(Exception e) {
        return "This is a fallback method yet to be implemented";
    }

}
