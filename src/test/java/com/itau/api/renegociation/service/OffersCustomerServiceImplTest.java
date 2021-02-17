package com.itau.api.renegociation.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.api.renegociation.dto.OffersCustomerRequestDTO;
import com.itau.api.renegociation.exception.NotFoundException;
import com.itau.api.renegociation.factory.OffersCustomerFactory;
import com.itau.api.renegociation.mock.CustomerModelMock;
import com.itau.api.renegociation.mock.OffersCustomerMock;
import com.itau.api.renegociation.model.CustomerModel;
import com.itau.api.renegociation.service.impl.OffersCustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class OffersCustomerServiceImplTest {

    @Mock
    private CustomerService customerService;

    @Mock
    private EventProducerService eventProducerService;

    @Mock
    private OffersCustomerFactory offersCustomerFactory;

    @InjectMocks
    private OffersCustomerServiceImpl offersCustomerServiceImpl;


    @Test
    void offers() throws JsonProcessingException {
        String document = "99999999999";
        OffersCustomerRequestDTO request = OffersCustomerMock.getRequestOffersCustomerRequest();
        CustomerModel customerModel = CustomerModelMock.getCustomerModel();
        Mockito.when(customerService.findCustomer(document)).thenReturn(Optional.of(customerModel));
        Mockito.when(offersCustomerFactory.convertOffersCustomerModelToOffersResponse(customerModel)).thenReturn(OffersCustomerMock.getOffersCustomerResponse());
        assertNotNull(offersCustomerServiceImpl.offers(request));
    }

    @Test
    void offersNotFound() throws JsonProcessingException {
        OffersCustomerRequestDTO request = OffersCustomerMock.getRequestOffersCustomerRequest();
        assertThrows(NotFoundException.class, () ->{
            offersCustomerServiceImpl.offers(request);
        });
    }


}
