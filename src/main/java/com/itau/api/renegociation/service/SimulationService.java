package com.itau.api.renegociation.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.api.renegociation.dto.SimulationRequestDTO;
import com.itau.api.renegociation.dto.SimulationResponseDTO;

public interface SimulationService {
    SimulationResponseDTO simulation(SimulationRequestDTO simulationRequest) throws JsonProcessingException;
}
