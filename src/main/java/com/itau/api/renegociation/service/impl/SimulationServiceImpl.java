package com.itau.api.renegociation.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.api.renegociation.constant.Constants;
import com.itau.api.renegociation.dto.SimulationRequestDTO;
import com.itau.api.renegociation.dto.SimulationResponseDTO;
import com.itau.api.renegociation.enums.TopicEnum;
import com.itau.api.renegociation.exception.NotFoundException;
import com.itau.api.renegociation.factory.SimulationFactory;
import com.itau.api.renegociation.service.CustomerService;
import com.itau.api.renegociation.service.EventProducerService;
import com.itau.api.renegociation.service.SimulationService;
import org.springframework.stereotype.Service;

@Service
public class SimulationServiceImpl implements SimulationService {

    private final CustomerService customerService;
    private final EventProducerService eventProducerService;
    private final SimulationFactory simulationFactory;

    public SimulationServiceImpl(final CustomerService customerService,
                                    final EventProducerService eventProducerService,
                                    final SimulationFactory simulationFactory) {
        this.customerService = customerService;
        this.eventProducerService = eventProducerService;
        this.simulationFactory = simulationFactory;
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
        return this.simulationFactory.convertSimulationModelToSimulationResponse(simulationRequest);
    }

    private void sendEvent(SimulationResponseDTO simulationResponse) throws JsonProcessingException {
        this.eventProducerService.send(
                this.simulationFactory.convertSimulationResponseToString(simulationResponse)
                , TopicEnum.simulacaoRenegociacao);
    }
}
