package com.itau.api.renegociation.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.api.renegociation.constant.Constants;
import com.itau.api.renegociation.dto.EffectiveRequestDTO;
import com.itau.api.renegociation.dto.EffectiveResponseDTO;
import com.itau.api.renegociation.enums.TopicEnum;
import com.itau.api.renegociation.exception.NotFoundException;
import com.itau.api.renegociation.factory.RenegociationFactory;
import com.itau.api.renegociation.service.CustomerService;
import com.itau.api.renegociation.service.EventProducerService;
import com.itau.api.renegociation.service.RenegociationService;
import org.springframework.stereotype.Service;

@Service
public class RenegociationServiceImpl implements RenegociationService {

    private final CustomerService customerService;
    private final EventProducerService eventProducerService;
    private final RenegociationFactory renegociationFactory;

    public RenegociationServiceImpl(final CustomerService customerService,
                                    final EventProducerService eventProducerService,
                                    final RenegociationFactory renegociationFactory) {
        this.customerService = customerService;
        this.eventProducerService = eventProducerService;
        this.renegociationFactory = renegociationFactory;
    }
    @Override
    public EffectiveResponseDTO renegociation(EffectiveRequestDTO effectiveRequest) throws JsonProcessingException {
        validateCustomer(effectiveRequest);
        EffectiveResponseDTO effectiveResponse = effective(effectiveRequest);
        sendEvent(effectiveResponse);
        return effectiveResponse;
    }

    private void validateCustomer(EffectiveRequestDTO effectiveRequest) {
        if (this.customerService.findCustomer(effectiveRequest.getDocument()).isEmpty()) {
            throw new NotFoundException(String.format(Constants.MSG_NOT_FOUND, effectiveRequest.getDocument()));
        }
    }

    private EffectiveResponseDTO effective(EffectiveRequestDTO effectiveRequest) {
        return this.renegociationFactory.convertRenegociationModelToEffectiveResponse(
                effectiveRequest);
    }

    private void sendEvent(EffectiveResponseDTO effectiveResponse) throws JsonProcessingException {
        this.eventProducerService.send(
                this.renegociationFactory.convertEffectiveResponseToString(effectiveResponse)
                , TopicEnum.efetivarRenegociacao);
    }
}
