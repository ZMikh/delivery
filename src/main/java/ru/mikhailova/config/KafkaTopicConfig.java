package ru.mikhailova.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic cancelMessageTopic() {
        return TopicBuilder.name("cancelMessage")
                .build();
    }

    @Bean
    public NewTopic deliveryFinishTopic() {
        return TopicBuilder.name("deliveryFinishMessage")
                .build();
    }
}
