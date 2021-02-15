package com.itau.api.renegociation.factory.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.api.renegociation.constant.Constants;
import com.itau.api.renegociation.dto.EffectiveResponseDTO;
import com.itau.api.renegociation.factory.RenegociationFactory;
import com.itau.api.renegociation.model.EffectiveRenegociationModel;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RenegociationFactoryImpl implements RenegociationFactory {
    @Override
    public EffectiveResponseDTO convertRenegociationModelToEffectiveResponse(EffectiveRenegociationModel effectiveRenegociationModel) {
        return EffectiveResponseDTO
                .builder()
                .idTransaction(effectiveRenegociationModel.getId())
                .message(Constants.MSG_PROCESSANDO)
                .date(effectiveRenegociationModel.getDate())
                .build();

    }

    @Override
    public String convertEffectiveResponseToString(EffectiveResponseDTO effectiveResponse) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(effectiveResponse);
    }
}
