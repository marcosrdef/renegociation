package com.itau.api.renegociation.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.api.renegociation.dto.SimulationRequestDTO;
import com.itau.api.renegociation.dto.SimulationResponseDTO;
import com.itau.api.renegociation.factory.impl.SimulationFactoryImpl;
import com.itau.api.renegociation.mock.SimulationMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class SimulationFactoryImplTest {

    @InjectMocks
    private SimulationFactoryImpl simulationFactoryImpl;

    @Test
    void testConvertSimulationModelToSimulationResponse() {
        SimulationRequestDTO request = SimulationMock.getSimulationRequest();
        assertNotNull(simulationFactoryImpl.convertSimulationModelToSimulationResponse(request));
    }

    @Test
    void testConvertSimulationResponseToString() throws JsonProcessingException {
        SimulationResponseDTO request = SimulationMock.getSimulationResponse();
        assertNotNull(simulationFactoryImpl.convertSimulationResponseToString(request));
    }

}
