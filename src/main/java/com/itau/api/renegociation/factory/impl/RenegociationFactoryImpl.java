package com.itau.api.renegociation.factory.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.api.renegociation.constant.Constants;
import com.itau.api.renegociation.dto.EffectiveRequestDTO;
import com.itau.api.renegociation.dto.EffectiveResponseDTO;
import com.itau.api.renegociation.factory.RenegociationFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RenegociationFactoryImpl implements RenegociationFactory {
    @Override
    public EffectiveResponseDTO convertRenegociationModelToEffectiveResponse(EffectiveRequestDTO effectiveRequest) {
        return EffectiveResponseDTO
                .builder()
                .transactionId(java.util.UUID.randomUUID().toString())
                .message(Constants.MSG_PROCESSANDO)
                .date(new Date().toString())
                .documentId(effectiveRequest.getDocument())
                .simulationId(effectiveRequest.getSimulationId())
                .groupSimulationId(effectiveRequest.getGroupSimulationId())
                .build();

    }

    @Override
    public String convertEffectiveResponseToString(EffectiveResponseDTO effectiveResponse) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(effectiveResponse);
    }
}
