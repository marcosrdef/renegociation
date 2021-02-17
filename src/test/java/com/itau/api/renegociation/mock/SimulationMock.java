package com.itau.api.renegociation.mock;

import com.itau.api.renegociation.dto.SimulationRequestDTO;
import com.itau.api.renegociation.dto.SimulationResponseDTO;

public class SimulationMock {
    public static SimulationRequestDTO getSimulationRequest() {
       return SimulationRequestDTO.builder()
                .document("99999999999")
                .build();
    }

    public static SimulationResponseDTO getSimulationResponse() {
        return SimulationResponseDTO.builder()
                .documentId("99999999999")
                .groupSimulationId("bada77b2-c775-438e-b75d-a4fb0b8b4741")
                .message("Processando solicitação")
                .date("Tue Feb 16 21:49:09 BRT 2021")
                .build();
    }
}
