package com.itau.api.renegociation.service.impl;

import com.itau.api.renegociation.constant.Constants;
import com.itau.api.renegociation.model.OffersCustomerModel;
import com.itau.api.renegociation.repository.OffersCustomerModelRepository;
import com.itau.api.renegociation.service.OffersCustomerModelService;
import org.springframework.stereotype.Service;

@Service
public class OffersCustomerServiceModelImpl implements OffersCustomerModelService {

    private final OffersCustomerModelRepository offersCustomerModelRepository;

    public OffersCustomerServiceModelImpl(final OffersCustomerModelRepository offersCustomerModelRepository) {
        this.offersCustomerModelRepository = offersCustomerModelRepository;
    }

    @Override
    public OffersCustomerModel offers(String document) {
        return offersCustomerModelRepository.save(
                OffersCustomerModel
                .builder()
                .documentId(document)
                .status(Constants.STATUS_PROCESSANDO)
                .build()
        );
    }
}
