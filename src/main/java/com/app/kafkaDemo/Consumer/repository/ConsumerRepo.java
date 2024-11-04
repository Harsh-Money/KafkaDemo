package com.app.kafkaDemo.Consumer.repository;

import com.app.kafkaDemo.Consumer.bo.ConsumerMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumerRepo extends JpaRepository<ConsumerMessage, Integer> {


}
