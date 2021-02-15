package com.itau.api.renegociation.service;


import com.itau.api.renegociation.enums.TopicEnum;
import org.springframework.web.bind.annotation.RequestBody;

public interface EventProducerService {
    void send(final @RequestBody String message, TopicEnum topic);
}
