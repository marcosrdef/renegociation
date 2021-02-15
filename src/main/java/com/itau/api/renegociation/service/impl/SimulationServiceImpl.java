package com.itau.api.renegociation.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.api.renegociation.constant.Constants;
import com.itau.api.renegociation.dto.SimulationRequestDTO;
import com.itau.api.renegociation.dto.SimulationResponseDTO;
import com.itau.api.renegociation.enums.TopicEnum;
import com.itau.api.renegociation.exception.NotFoundException;
import com.itau.api.renegociation.factory.impl.SimulationFactoryImpl;
import com.itau.api.renegociation.service.*;
import org.springframework.stereotype.Service;

@Service
public class SimulationServiceImpl implements SimulationService {

    private final CustomerService customerService;
    private final SimulationModelService simulationModelService;
    private final EventProducerService eventProducerService;
    private final SimulationFactoryImpl simulationFactoryImpl;

    public SimulationServiceImpl(final CustomerService customerService,
                                    final SimulationModelService simulationModelService,
                                    final EventProducerService eventProducerService,
                                    final SimulationFactoryImpl simulationFactoryImpl) {
        this.customerService = customerService;
        this.simulationModelService = simulationModelService;
        this.eventProducerService = eventProducerService;
        this.simulationFactoryImpl = simulationFactoryImpl;
    }

    @Override
    public SimulationResponseDTO simulation(SimulationRequestDTO simulationRequest) throws JsonProcessingException {
        validateCustomer(simulationRequest);
        SimulationResponseDTO SimulationResponse = simulate(simulationRequest);
        sendEvent(SimulationResponse);
        return SimulationResponse;
    }

    private void validateCustomer(SimulationRequestDTO simulationRequest) {
        if (this.customerService.findCustomer(simulationRequest.getDocument()).isEmpty()) {
            throw new NotFoundException(String.format(Constants.MSG_NOT_FOUND, simulationRequest.getDocument()));
        }
    }

    private SimulationResponseDTO simulate(SimulationRequestDTO simulationRequest) {
        return this.simulationFactoryImpl.convertSimulationModelToSimulationResponse(
                this.simulationModelService.simulation(simulationRequest.getDocument()));
    }

    private void sendEvent(SimulationResponseDTO simulationResponse) throws JsonProcessingException {
        this.eventProducerService.send(
                this.simulationFactoryImpl.convertSimulationResponseToString(simulationResponse)
                , TopicEnum.simulacaoRenegociacao);
    }
}
