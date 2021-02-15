package com.itau.api.renegociation.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.api.renegociation.dto.EffectiveResponseDTO;
import com.itau.api.renegociation.model.EffectiveRenegociationModel;

public interface RenegociationFactory {
    EffectiveResponseDTO convertRenegociationModelToEffectiveResponse(EffectiveRenegociationModel effectiveRenegociationModel);
    String convertEffectiveResponseToString(EffectiveResponseDTO effectiveResponse) throws JsonProcessingException;
}
