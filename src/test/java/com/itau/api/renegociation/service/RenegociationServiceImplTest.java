package com.itau.api.renegociation.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.api.renegociation.dto.EffectiveRequestDTO;
import com.itau.api.renegociation.exception.NotFoundException;
import com.itau.api.renegociation.factory.RenegociationFactory;
import com.itau.api.renegociation.mock.CustomerModelMock;
import com.itau.api.renegociation.mock.RenegociationMock;
import com.itau.api.renegociation.model.CustomerModel;
import com.itau.api.renegociation.service.impl.RenegociationServiceImpl;
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
public class RenegociationServiceImplTest {

    @Mock
    private CustomerService customerService;

    @Mock
    private EventProducerService eventProducerService;

    @Mock
    private RenegociationFactory renegociationFactory;

    @InjectMocks
    private RenegociationServiceImpl renegociationServiceImpl;

    @Test
    void testEffective() throws JsonProcessingException {
        String document = "99999999999";
        EffectiveRequestDTO request = RenegociationMock.getEffectiveRequest();
        CustomerModel customerModel = CustomerModelMock.getCustomerModel();
        Mockito.when(customerService.findCustomer(document)).thenReturn(Optional.of(customerModel));
        Mockito.when(renegociationFactory.convertRenegociationModelToEffectiveResponse(request)).thenReturn(RenegociationMock.getEffectiveResponse());
        assertNotNull(renegociationServiceImpl.renegociation(request));
    }

    @Test
    void effectiveNotFound() throws JsonProcessingException {
        EffectiveRequestDTO request = RenegociationMock.getEffectiveRequest();
        assertThrows(NotFoundException.class, () ->{
            renegociationServiceImpl.renegociation(request);
        });
    }

}
