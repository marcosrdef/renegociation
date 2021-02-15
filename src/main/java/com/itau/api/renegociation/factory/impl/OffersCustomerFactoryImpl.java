package com.itau.api.renegociation.factory.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.api.renegociation.constant.Constants;
import com.itau.api.renegociation.dto.OffersCustomerResponseDTO;
import com.itau.api.renegociation.factory.OffersCustomerFactory;
import com.itau.api.renegociation.model.OffersCustomerModel;
import org.springframework.stereotype.Service;

@Service
public class OffersCustomerFactoryImpl implements OffersCustomerFactory {
    @Override
    public OffersCustomerResponseDTO convertOffersCustomerModelToOffersResponse(OffersCustomerModel offersCustomerModel) {
        return OffersCustomerResponseDTO
                .builder()
                .transactionId(offersCustomerModel.getId())
                .date(offersCustomerModel.getDate())
                .message(Constants.MSG_PROCESSANDO)
                .build();
    }

    @Override
    public String convertOffersCustomerModelToString(OffersCustomerResponseDTO offersCustomerResponse) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(offersCustomerResponse);
    }
}
