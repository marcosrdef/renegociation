package com.itau.api.renegociation.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.api.renegociation.dto.OffersCustomerResponseDTO;
import com.itau.api.renegociation.model.OffersCustomerModel;

public interface OffersCustomerFactory {
    OffersCustomerResponseDTO convertOffersCustomerModelToOffersResponse(OffersCustomerModel offersCustomerModel);
    String convertOffersCustomerModelToString(OffersCustomerResponseDTO offersCustomerResponse) throws JsonProcessingException;
}
