package com.itau.api.renegociation.service;

import com.itau.api.renegociation.model.CustomerModel;

import java.util.Optional;

public interface CustomerService {
    Optional<CustomerModel> findCustomer(String document);
}
