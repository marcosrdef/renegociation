package com.itau.api.renegociation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EffectiveResponseDTO {
    private String transactionId;
    private String date;
    private String message;
    private String documentId;
    private String simulationId;
    private String groupSimulationId;
}
