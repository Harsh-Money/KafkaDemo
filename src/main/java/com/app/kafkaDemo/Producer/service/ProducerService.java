package com.app.kafkaDemo.Producer.service;

import com.app.kafkaDemo.Consumer.bo.ConsumerMessage;
import com.app.kafkaDemo.Consumer.repository.ConsumerRepo;
import com.app.kafkaDemo.Producer.bo.ProducerMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ProducerService {

    @Autowired
    private KafkaTemplate<String, ProducerMessage> kafkaTemplate;

    @Autowired
    private ConsumerRepo consumerRepo;

    public void send(String topicName, ProducerMessage message){

        Message<ProducerMessage> producerMessage = MessageBuilder
                .withPayload(message)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();


//        CompletableFuture<SendResult<String, ProducerMessage>> future = kafkaTemplate.send(producerMessage);
//        future.whenComplete((sendResult, exception) -> {
//            if(exception != null) {
//                future.completeExceptionally(exception);
//            } else {
//                System.out.println("send message: "+message.getMsg()+" "+message.getId());
//                future.complete(sendResult);
//            }
//
//        });
        kafkaTemplate.send(producerMessage);
    }

    public String normalCheck(String s) {
        System.out.println("Data at normal check: "+s);
        ConsumerMessage cm = new ConsumerMessage();
        cm.setMsg(s);
        consumerRepo.save(cm);
        return "Done";
    }
}
