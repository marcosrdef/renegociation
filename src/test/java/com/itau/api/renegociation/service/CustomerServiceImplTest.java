package com.itau.api.renegociation.service;

import com.itau.api.renegociation.mock.CustomerModelMock;
import com.itau.api.renegociation.mock.SimulationMock;
import com.itau.api.renegociation.model.CustomerModel;
import com.itau.api.renegociation.repository.CustomerModelRepository;
import com.itau.api.renegociation.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerModelRepository customerModelRepository;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    @Test
    void findCustomer() {
        String document = "99999999999";
        CustomerModel customerModel = CustomerModelMock.getCustomerModel();
        Mockito.when(customerModelRepository.findByDocumentId(document)).thenReturn(Optional.of(customerModel));
        assertNotNull(customerServiceImpl.findCustomer(document));
    }
}
