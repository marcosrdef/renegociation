package com.itau.api.renegociation.mock;

import com.itau.api.renegociation.dto.EffectiveRequestDTO;
import com.itau.api.renegociation.dto.EffectiveResponseDTO;

public class RenegociationMock {
    public static EffectiveRequestDTO getEffectiveRequest() {
        return EffectiveRequestDTO.builder()
                .document("99999999999")
                .build();
    }

    public static EffectiveResponseDTO getEffectiveResponse() {
        return EffectiveResponseDTO.builder()
                .transactionId("46493ac1-ab32-4077-9f80-8966ed0f4a53")
                .date("Tue Feb 16 21:52:29 BRT 2021")
                .message("Processando solicitação")
                .simulationId("5b92d428-c46f-43b2-a521-ea7b33b3a637")
                .groupSimulationId("bada77b2-c775-438e-b75d-a4fb0b8b4741")
                .documentId("99999999999")
                .build();
    }
}
