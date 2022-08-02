package com.donkey.kafkademo.applications.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProducerService {
    private static final String DEMO_TOPIC = "demoTopic";
    private final KafkaTemplate<String, String> kafkaTemplate;
    
    public void sendString(String message) {
        log.info("sending String: {}", message);
        kafkaTemplate.send(DEMO_TOPIC, message);
    }
}
