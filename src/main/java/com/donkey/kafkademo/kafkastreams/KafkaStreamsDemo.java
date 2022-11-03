package com.donkey.kafkademo.kafkastreams;

import com.donkey.kafkademo.Topics;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class KafkaStreamsDemo {
    
//    private final KStream<String, String> stream;
    private final KTable<String, String> kt;
    private final Map<String, String> store = new HashMap<>();
    public String target = "";
    
    public KafkaStreamsDemo(StreamsBuilder builder) {
        
        kt = builder.table(Topics.DEMO_TOPIC, Materialized.with(Serdes.String(), Serdes.String()));
        kt.mapValues(value -> value.replaceAll("\"", ""))
          .filter((key, value) -> {
              try {
                  log.info("processing value: {}", value);
                  int i = Integer.parseInt(value);
                  log.info("i: {}", i);
                  return true;
              } catch (Exception ignore) {
                  log.warn("key: {}, value: {}", key, value);
                  return false;
              }
          })
          .toStream()
          .peek((key, value) -> log.info("current target: {}", target))
          .peek((key, value) -> {
                    log.info("key: {}, value: {}", key, value);
                    target = target.concat(value).concat("-");
          });
    }
    
    public String getValue(String key) {
        String value = store.get(key);
        log.info("key: {} 에 대해 저장된 값을 반환합니다.: {}", key, value);
        return value;
    }
    
    public String getIntegers(){
        log.info("getting integers");
        return target;
    }
}
