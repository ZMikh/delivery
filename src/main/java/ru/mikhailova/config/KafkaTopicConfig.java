package ru.mikhailova.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    private final String cancelMessageTopic;
    private final String deliveryFinishMessageTopic;

    public KafkaTopicConfig(@Value("${kafka.topic.cancel-message}") String cancelMessageTopic,
                            @Value("${kafka.topic.delivery-finish-message}") String deliveryFinishMessageTopic) {
        this.cancelMessageTopic = cancelMessageTopic;
        this.deliveryFinishMessageTopic = deliveryFinishMessageTopic;
    }

    @Bean
    public NewTopic cancelMessageTopic() {
        return TopicBuilder.name(cancelMessageTopic)
                .build();
    }

    @Bean
    public NewTopic deliveryFinishTopic() {
        return TopicBuilder.name(deliveryFinishMessageTopic)
                .build();
    }
}
