package com.itau.api.renegociation.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.api.renegociation.dto.EffectiveRequestDTO;
import com.itau.api.renegociation.dto.EffectiveResponseDTO;
import com.itau.api.renegociation.factory.impl.RenegociationFactoryImpl;
import com.itau.api.renegociation.mock.RenegociationMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class RenegociationFactoryImplTest {

    @InjectMocks
    private RenegociationFactoryImpl renegociationFactoryImpl;

    @Test
    void testConvertRenegociationModelToEffectiveResponse() {
        EffectiveRequestDTO request = RenegociationMock.getEffectiveRequest();
        assertNotNull(renegociationFactoryImpl.convertRenegociationModelToEffectiveResponse(request));
    }

    @Test
    void testConvertEffectiveResponseToString() throws JsonProcessingException {
        EffectiveResponseDTO request = RenegociationMock.getEffectiveResponse();
        assertNotNull(renegociationFactoryImpl.convertEffectiveResponseToString(request));
    }
}
