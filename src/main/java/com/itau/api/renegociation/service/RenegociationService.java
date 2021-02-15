package com.itau.api.renegociation.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.api.renegociation.dto.EffectiveRequestDTO;
import com.itau.api.renegociation.dto.EffectiveResponseDTO;

public interface RenegociationService {
    EffectiveResponseDTO renegociation(EffectiveRequestDTO effectiveRequest) throws JsonProcessingException;
}
