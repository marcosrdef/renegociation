package com.itau.api.renegociation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RenegociationApplicationTest {

    @InjectMocks
    private RenegociationApplication renegociationApplication;

    @Test
    void testMain() {
        renegociationApplication.main(new String[]{});
    }
}
