package com.itau.api.renegociation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimulationResponseDTO {
    private String groupSimulationId;
    private String documentId;
    private String date;
    private String message;
}
