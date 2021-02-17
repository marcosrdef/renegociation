package com.itau.api.renegociation.mock;

import com.itau.api.renegociation.dto.OffersCustomerRequestDTO;
import com.itau.api.renegociation.dto.OffersCustomerResponseDTO;

public class OffersCustomerMock {
    public static OffersCustomerRequestDTO getRequestOffersCustomerRequest() {
        return OffersCustomerRequestDTO.builder()
                .document("99999999999")
                .build();
    }

    public static OffersCustomerResponseDTO getOffersCustomerResponse() {
        return OffersCustomerResponseDTO.builder()
                .groupOffersId("38f89288-33c7-40b5-9f89-d6ef1f82eb60")
                .date("Tue Feb 16 21:52:29 BRT 2021")
                .message("Processando solicitação")
                .documentId("99999999999")
                .customerType("2")
                .build();
    }
}
