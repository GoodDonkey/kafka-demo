package com.donkey.kafkademo.applications.producer;

import com.donkey.kafkademo.Topics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.donkey.kafkademo.Topics.JSON_TOPIC;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, SomeMessage> someMessageKafkaTemplate;
    
    public void sendString(String key, String message) {
        log.info("sending key: {}, String: {}", key, message);
        kafkaTemplate.send(Topics.DEMO_TOPIC, key, message);
    }
    
    public void sendSomeMessage(SomeMessage message) {
        log.info("sending SomeMessage: {}", message);
        someMessageKafkaTemplate.send(JSON_TOPIC, message);
    }
}
