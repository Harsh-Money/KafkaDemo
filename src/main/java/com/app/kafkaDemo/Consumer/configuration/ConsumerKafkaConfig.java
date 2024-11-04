package com.app.kafkaDemo.Consumer.configuration;

import com.app.kafkaDemo.Consumer.bo.ConsumerMessage;
import com.app.kafkaDemo.Producer.bo.ProducerMessage;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class ConsumerKafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootStrapServer;

    public ConsumerFactory<String, ProducerMessage> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "id");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, "1024");
        config.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, "100");
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "com.app.kafkaDemo.Producer.bo");
        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.app.kafkaDemo.Producer.bo.ProducerMessage");

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(ProducerMessage.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ProducerMessage> concurrentKafkaListenerContainerFactory(
            ConsumerFactory<String, ProducerMessage> consumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<String, ProducerMessage> factory = new
                ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        System.out.println("factory of concurrent-consumer made");
        return factory;
    }
}
