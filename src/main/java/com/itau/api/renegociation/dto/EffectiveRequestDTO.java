package com.itau.api.renegociation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EffectiveRequestDTO {
    @Pattern(regexp = "\\d+")
    private String document;
    private String simulationId;
    private String groupSimulationId;
}
