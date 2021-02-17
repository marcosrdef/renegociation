package com.itau.api.renegociation.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.api.renegociation.dto.OffersCustomerResponseDTO;
import com.itau.api.renegociation.factory.impl.OffersCustomerFactoryImpl;
import com.itau.api.renegociation.mock.CustomerModelMock;
import com.itau.api.renegociation.mock.OffersCustomerMock;
import com.itau.api.renegociation.model.CustomerModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class OffersCustomerFactoryImplTest {

    @InjectMocks
    private OffersCustomerFactoryImpl offersCustomerFactoryImpl;

    @Test
    void testConvertOffersCustomerModelToOffersResponse() {
        CustomerModel request = CustomerModelMock.getCustomerModel();
        assertNotNull(offersCustomerFactoryImpl.convertOffersCustomerModelToOffersResponse(request));
    }

    @Test
    void testConvertOffersCustomerModelToString() throws JsonProcessingException {
        OffersCustomerResponseDTO request = OffersCustomerMock.getOffersCustomerResponse();
        assertNotNull(offersCustomerFactoryImpl.convertOffersCustomerModelToString(request));
    }
}
