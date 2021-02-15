package com.itau.api.renegociation.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.api.renegociation.dto.OffersCustomerRequestDTO;
import com.itau.api.renegociation.dto.OffersCustomerResponseDTO;

public interface OffersCustomerService {
    OffersCustomerResponseDTO offers(OffersCustomerRequestDTO offersCustomerRequest) throws JsonProcessingException;
}
