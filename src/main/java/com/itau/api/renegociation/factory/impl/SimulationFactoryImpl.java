package com.itau.api.renegociation.factory.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.api.renegociation.constant.Constants;
import com.itau.api.renegociation.dto.SimulationRequestDTO;
import com.itau.api.renegociation.dto.SimulationResponseDTO;
import com.itau.api.renegociation.factory.SimulationFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SimulationFactoryImpl implements SimulationFactory {
    @Override
    public SimulationResponseDTO convertSimulationModelToSimulationResponse(SimulationRequestDTO simulationRequest) {
        return SimulationResponseDTO
                .builder()
                .groupSimulationId(java.util.UUID.randomUUID().toString())
                .message(Constants.MSG_PROCESSANDO)
                .date(new Date().toString())
                .documentId(simulationRequest.getDocument())
                .build();
    }

    @Override
    public String convertSimulationResponseToString(SimulationResponseDTO simulationResponse) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(simulationResponse);
    }
}
