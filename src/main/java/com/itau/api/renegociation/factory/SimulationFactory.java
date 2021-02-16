package com.itau.api.renegociation.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.api.renegociation.dto.SimulationRequestDTO;
import com.itau.api.renegociation.dto.SimulationResponseDTO;

public interface SimulationFactory {
    SimulationResponseDTO convertSimulationModelToSimulationResponse(SimulationRequestDTO simulationRequest);
    String convertSimulationResponseToString(SimulationResponseDTO simulationResponse) throws JsonProcessingException;
}
