package com.itau.api.renegociation.service.impl;

import com.itau.api.renegociation.enums.TopicEnum;
import com.itau.api.renegociation.service.EventProducerService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class EventProducerServiceImpl implements EventProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    public EventProducerServiceImpl(final KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    @Override
    public void send(final @RequestBody String message, TopicEnum topic) {
        final String messageKey = java.util.UUID.randomUUID().toString();
        this.kafkaTemplate.send(topic.toString(), messageKey, message);
    }
}
