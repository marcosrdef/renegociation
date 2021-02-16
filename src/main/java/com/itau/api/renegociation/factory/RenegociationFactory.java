package com.itau.api.renegociation.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.api.renegociation.dto.EffectiveRequestDTO;
import com.itau.api.renegociation.dto.EffectiveResponseDTO;

public interface RenegociationFactory {
    EffectiveResponseDTO convertRenegociationModelToEffectiveResponse(EffectiveRequestDTO effectiveRequest);
    String convertEffectiveResponseToString(EffectiveResponseDTO effectiveResponse) throws JsonProcessingException;
}
