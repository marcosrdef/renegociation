package com.itau.api.renegociation.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.api.renegociation.dto.OffersCustomerResponseDTO;
import com.itau.api.renegociation.model.CustomerModel;

public interface OffersCustomerFactory {
    OffersCustomerResponseDTO convertOffersCustomerModelToOffersResponse(CustomerModel customerModel);
    String convertOffersCustomerModelToString(OffersCustomerResponseDTO offersCustomerResponse) throws JsonProcessingException;
}
