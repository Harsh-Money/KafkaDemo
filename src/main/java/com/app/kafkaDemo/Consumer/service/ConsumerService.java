package com.app.kafkaDemo.Consumer.service;

import com.app.kafkaDemo.Consumer.bo.ConsumerMessage;
import com.app.kafkaDemo.Consumer.repository.ConsumerRepo;
import com.app.kafkaDemo.Producer.bo.ProducerMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepo consumerRepo;

    @KafkaListener(topics = "message-producer", groupId = "id")
    public void consumeMessage(ConsumerRecord<String, ProducerMessage> record) {
//        System.out.println("Received _TypeId_ header: " + typeId);
        System.out.println("Message payload: " + record.value());
//        consumerRepo.save(cm);

    }
}
