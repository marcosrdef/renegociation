package com.itau.api.renegociation.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.api.renegociation.dto.SimulationRequestDTO;
import com.itau.api.renegociation.exception.NotFoundException;
import com.itau.api.renegociation.factory.SimulationFactory;
import com.itau.api.renegociation.mock.CustomerModelMock;
import com.itau.api.renegociation.mock.SimulationMock;
import com.itau.api.renegociation.model.CustomerModel;
import com.itau.api.renegociation.service.impl.SimulationServiceImpl;
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
public class SimulationServiceImplTest {

    @Mock
    private CustomerService customerService;

    @Mock
    private EventProducerService eventProducerService;

    @Mock
    private SimulationFactory simulationFactory;

    @InjectMocks
    private SimulationServiceImpl simulationServiceImpl;


    @Test
    void simulation() throws JsonProcessingException {
        String document = "99999999999";
        SimulationRequestDTO request = SimulationMock.getSimulationRequest();
        CustomerModel customerModel = CustomerModelMock.getCustomerModel();
        Mockito.when(customerService.findCustomer(document)).thenReturn(Optional.of(customerModel));
        Mockito.when(simulationFactory.convertSimulationModelToSimulationResponse(request)).thenReturn(SimulationMock.getSimulationResponse());
        assertNotNull(simulationServiceImpl.simulation(request));
    }

    @Test
    void simulationNotFound() throws JsonProcessingException {
        SimulationRequestDTO request = SimulationMock.getSimulationRequest();
        assertThrows(NotFoundException.class, () ->{
            simulationServiceImpl.simulation(request);
        });
    }
}
