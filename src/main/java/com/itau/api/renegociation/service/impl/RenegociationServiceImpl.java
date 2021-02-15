package com.itau.api.renegociation.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.api.renegociation.constant.Constants;
import com.itau.api.renegociation.dto.EffectiveRequestDTO;
import com.itau.api.renegociation.dto.EffectiveResponseDTO;
import com.itau.api.renegociation.enums.TopicEnum;
import com.itau.api.renegociation.exception.NotFoundException;
import com.itau.api.renegociation.factory.impl.RenegociationFactoryImpl;
import com.itau.api.renegociation.service.CustomerService;
import com.itau.api.renegociation.service.EffectiveRenegociationModelService;
import com.itau.api.renegociation.service.EventProducerService;
import com.itau.api.renegociation.service.RenegociationService;
import org.springframework.stereotype.Service;

@Service
public class RenegociationServiceImpl implements RenegociationService {

    private final CustomerService customerService;
    private final EffectiveRenegociationModelService effectiveRenegociationModelService;
    private final EventProducerService eventProducerService;
    private final RenegociationFactoryImpl renegociationFactoryImpl;

    public RenegociationServiceImpl(final CustomerService customerService,
                                    final EffectiveRenegociationModelService effectiveRenegociationModelService,
                                    final EventProducerService eventProducerService,
                                    final RenegociationFactoryImpl renegociationFactoryImpl) {
        this.customerService = customerService;
        this.effectiveRenegociationModelService = effectiveRenegociationModelService;
        this.eventProducerService = eventProducerService;
        this.renegociationFactoryImpl = renegociationFactoryImpl;
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
        return this.renegociationFactoryImpl.convertRenegociationModelToEffectiveResponse(
                this.effectiveRenegociationModelService.effective(effectiveRequest.getDocument()));
    }

    private void sendEvent(EffectiveResponseDTO effectiveResponse) throws JsonProcessingException {
        this.eventProducerService.send(
                this.renegociationFactoryImpl.convertEffectiveResponseToString(effectiveResponse)
                , TopicEnum.efetivarRenegociacao);
    }
}
