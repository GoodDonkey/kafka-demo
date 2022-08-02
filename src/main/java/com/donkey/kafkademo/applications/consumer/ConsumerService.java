package com.donkey.kafkademo.applications.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class ConsumerService {
    
    @KafkaListener(topics = "demoTopic", groupId = "demo")
    public void consume(String Message) throws IOException {
        log.info("consuming Message: {}", Message);
    }
}
