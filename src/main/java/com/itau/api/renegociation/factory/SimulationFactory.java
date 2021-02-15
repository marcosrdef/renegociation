package com.itau.api.renegociation.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.api.renegociation.dto.SimulationResponseDTO;
import com.itau.api.renegociation.model.SimulationModel;

public interface SimulationFactory {
    SimulationResponseDTO convertSimulationModelToSimulationResponse(SimulationModel simulationModel);
    String convertSimulationResponseToString(SimulationResponseDTO simulationResponse) throws JsonProcessingException;
}
