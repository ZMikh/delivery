package ru.mikhailova;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class Main {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    @Bean
    CommandLineRunner commandLineRunner(KafkaTemplate<String, Object> kafkaTemplate) {
        return args -> {
          kafkaTemplate.send("topic", "fromCommandRunner");
        };
    }

    @Scheduled(cron = "0 0/15 * * * *")
    public void scheduler() {
        kafkaTemplate.send("topic", "hello");
    }
}