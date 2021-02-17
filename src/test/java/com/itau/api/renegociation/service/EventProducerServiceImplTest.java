package com.itau.api.renegociation.service;

import com.itau.api.renegociation.enums.TopicEnum;
import com.itau.api.renegociation.service.impl.EventProducerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

@ExtendWith(MockitoExtension.class)
public class EventProducerServiceImplTest {

    @InjectMocks
    private EventProducerServiceImpl eventProducerServiceImpl;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    void sendRenegociation() {
        eventProducerServiceImpl.send("teste 1", TopicEnum.efetivarRenegociacao);
    }

    @Test
    void sendSimulation() {
        eventProducerServiceImpl.send("teste 2", TopicEnum.simulacaoRenegociacao);
    }

    @Test
    void sendOffers() {
        eventProducerServiceImpl.send("teste 3", TopicEnum.listarProdutos);
    }
}
