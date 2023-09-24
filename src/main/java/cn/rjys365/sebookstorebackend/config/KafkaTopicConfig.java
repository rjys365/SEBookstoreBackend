package cn.rjys365.sebookstorebackend.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic orderRequestTopic() {
        return new NewTopic("order_request", 1, (short) 1);
    }

    @Bean
    public NewTopic orderResponseTopic() {
        return new NewTopic("order_response", 1, (short) 1);
    }
}
