package com.itau.api.renegociation.mock;

import com.itau.api.renegociation.model.CustomerModel;

public class CustomerModelMock {
    public static CustomerModel getCustomerModel() {
        return CustomerModel.builder()
                .birthDay("25/12/1995")
                .documentId("99999999999")
                .id("c4b5dd52-168e-414d-a482-58f92931bf3a")
                .enable("true")
                .lastName("Silva")
                .name("Pedro Henrique")
                .type("1")
                .typeDescription("SILVER")
                .build();
    }
}
