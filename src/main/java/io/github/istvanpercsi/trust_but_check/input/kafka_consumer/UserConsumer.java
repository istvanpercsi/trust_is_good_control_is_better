package io.github.istvanpercsi.trust_but_check.input.kafka_consumer;


import io.github.istvanpercsi.trust_but_check.input.kafka_consumer.model.CreateUserRequest;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

public class UserConsumer {



    @KafkaListener
    public void consumeRecord(ConsumerRecord<String, CreateUserRequest> consumerRecord, Acknowledgment acknowledgment) {
        CreateUserRequest createUserRequest = consumerRecord.value();

    }
}
