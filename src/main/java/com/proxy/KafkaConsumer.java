package com.proxy;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @KafkaListener(topics = "test", groupId = "registration")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group Mugambi: " + message);
    }
}
