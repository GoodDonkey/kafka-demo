package com.donkey.kafkademo.web;

import com.donkey.kafkademo.applications.producer.ProducerService;
import com.donkey.kafkademo.applications.producer.SomeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class KafkaController {
    
    private final ProducerService producerService;
    
    @PostMapping("/send")
    public String sendMessage(@RequestParam("message") String message) {
        log.debug("requesting message: {}", message);
        producerService.sendString(message);
        return "sent";
    }
    
    @PostMapping("/send/object")
    public String sendMessage(@RequestBody SomeMessage message) {
        log.debug("requesting message: {}", message);
        producerService.sendSomeMessage(message);
        return "SomeMessage sent";
    }
}

