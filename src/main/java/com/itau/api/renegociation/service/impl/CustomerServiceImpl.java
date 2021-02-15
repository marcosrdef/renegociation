package com.itau.api.renegociation.service.impl;

import com.itau.api.renegociation.model.CustomerModel;
import com.itau.api.renegociation.repository.CustomerModelRepository;
import com.itau.api.renegociation.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerModelRepository customerModelRepository;

    public CustomerServiceImpl(final CustomerModelRepository customerModelRepository) {
        this.customerModelRepository = customerModelRepository;
    }

    @Override
    public Optional<CustomerModel> findCustomer(String document) {
        return this.customerModelRepository.findByDocumentId(document);
    }
}
