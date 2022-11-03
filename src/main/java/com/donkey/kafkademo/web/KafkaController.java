package com.donkey.kafkademo.web;

import com.donkey.kafkademo.applications.producer.ProducerService;
import com.donkey.kafkademo.applications.producer.SomeMessage;
import com.donkey.kafkademo.kafkastreams.KafkaStreamsDemo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class KafkaController {
    
    private final ProducerService producerService;
    private final KafkaStreamsDemo kafkaStreamsDemo;
    
    @PostMapping("/send")
    public String sendMessage(@RequestParam("key") String key,
                              @RequestParam("message") String message) {
        log.info("requesting key {} and message: {}", key, message);
        producerService.sendString(key, message);
        return "sent";
    }
    
    @PostMapping("/send/object")
    public String sendMessage(@RequestBody SomeMessage message) {
        log.info("requesting message: {}", message);
        producerService.sendSomeMessage(message);
        return "SomeMessage sent";
    }
    
    @GetMapping("/get")
    public String getMessage(@RequestParam String key){
        log.debug("requesting message for key: {}", key);
        return kafkaStreamsDemo.getValue(key);
    }
    
    @GetMapping("/get-integers")
    public String getintegers(){
        log.debug("requesting message get Integers");
        return kafkaStreamsDemo.getIntegers();
    }
    
    
}

