package com.itau.api.renegociation.factory.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.api.renegociation.constant.Constants;
import com.itau.api.renegociation.dto.OffersCustomerResponseDTO;
import com.itau.api.renegociation.factory.OffersCustomerFactory;
import com.itau.api.renegociation.model.CustomerModel;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OffersCustomerFactoryImpl implements OffersCustomerFactory {
    @Override
    public OffersCustomerResponseDTO convertOffersCustomerModelToOffersResponse(CustomerModel customerModel) {
        return OffersCustomerResponseDTO
                .builder()
                .groupOffersId(java.util.UUID.randomUUID().toString())
                .date(new Date().toString())
                .message(Constants.MSG_PROCESSANDO)
                .documentId(customerModel.getDocumentId())
                .customerType(customerModel.getType())
                .build();
    }

    @Override
    public String convertOffersCustomerModelToString(OffersCustomerResponseDTO offersCustomerResponse) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(offersCustomerResponse);
    }
}
