package com.itau.api.renegociation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.api.renegociation.dto.EffectiveRequestDTO;
import com.itau.api.renegociation.dto.OffersCustomerRequestDTO;
import com.itau.api.renegociation.dto.SimulationRequestDTO;
import com.itau.api.renegociation.mock.OffersCustomerMock;
import com.itau.api.renegociation.mock.RenegociationMock;
import com.itau.api.renegociation.mock.SimulationMock;
import com.itau.api.renegociation.service.OffersCustomerService;
import com.itau.api.renegociation.service.RenegociationService;
import com.itau.api.renegociation.service.SimulationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class RenegociationControllerTest {

    @InjectMocks
    private RenegociationController renegociationController;

    @Mock
    private SimulationService simulationService;

    @Mock
    private RenegociationService renegociationService;

    @Mock
    private OffersCustomerService offersCustomerService;

    @Test
    void testSimulation() throws JsonProcessingException {
        SimulationRequestDTO request = SimulationMock.getSimulationRequest();
        Mockito.when(simulationService.simulation(request)).thenReturn(SimulationMock.getSimulationResponse());
        assertNotNull(renegociationController.simulation(request));
    }

    @Test
    void testRenegociation() throws JsonProcessingException {
        EffectiveRequestDTO request = RenegociationMock.getEffectiveRequest();
        Mockito.when(renegociationService.renegociation(request)).thenReturn(RenegociationMock.getEffectiveResponse());
        assertNotNull(renegociationController.renegociation(request));
    }

    @Test
    void testOffersCustomer() throws JsonProcessingException {
        OffersCustomerRequestDTO request = OffersCustomerMock.getRequestOffersCustomerRequest();
        Mockito.when(offersCustomerService.offers(request)).thenReturn(OffersCustomerMock.getOffersCustomerResponse());
        assertNotNull(renegociationController.offers(request));
    }
}
