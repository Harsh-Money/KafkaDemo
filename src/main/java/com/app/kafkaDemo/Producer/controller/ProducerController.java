package com.app.kafkaDemo.Producer.controller;

import com.app.kafkaDemo.Producer.bo.ProducerMessage;
import com.app.kafkaDemo.Producer.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/producer")
public class ProducerController {

    @Autowired
    private ProducerService producerService;

    @PostMapping
    public ResponseEntity<String> produce(@RequestBody ProducerMessage message){
        producerService.send("message-producer", message);
        System.out.println(message.getClass());
        return new ResponseEntity<>("Data Send", HttpStatus.OK);
    }

    @PostMapping("/check")
    public ResponseEntity<String> normal(@RequestBody String message) {
        producerService.normalCheck(message);
        return new ResponseEntity<>("Data recieved", HttpStatus.OK);
    }
}
