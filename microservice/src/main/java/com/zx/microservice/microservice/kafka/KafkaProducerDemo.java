package com.zx.microservice.microservice.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * kafka api实现
 *
 * @author zhouxin
 * @date 2018-12-08
 */
public class KafkaProducerDemo {
  /**
   * kafka default config {@link ProducerConfig#CONFIG}
   */
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    Properties properties = new Properties();
    properties.setProperty("bootstrap.servers", "localhost:9092");
    properties.setProperty("key.serializer", JsonSerializer.class.getName());
    properties.setProperty("value.serializer", JsonSerializer.class.getName());
    KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
    ProducerRecord<String, String> producerRecord = new ProducerRecord<>("zhouxin"
        , 0, System.currentTimeMillis(), "message-key", "heheda");
    Future<RecordMetadata> send = kafkaProducer.send(producerRecord);
    send.get();
  }
}
