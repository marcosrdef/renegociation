package com.itau.api.renegociation.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.api.renegociation.constant.Constants;
import com.itau.api.renegociation.dto.OffersCustomerRequestDTO;
import com.itau.api.renegociation.dto.OffersCustomerResponseDTO;
import com.itau.api.renegociation.enums.TopicEnum;
import com.itau.api.renegociation.exception.NotFoundException;
import com.itau.api.renegociation.factory.impl.OffersCustomerFactoryImpl;
import com.itau.api.renegociation.service.CustomerService;
import com.itau.api.renegociation.service.EventProducerService;
import com.itau.api.renegociation.service.OffersCustomerModelService;
import com.itau.api.renegociation.service.OffersCustomerService;
import org.springframework.stereotype.Service;

@Service
public class OffersCustomerServiceImpl implements OffersCustomerService {

    private final CustomerService customerService;
    private final OffersCustomerModelService offersCustomerModelService;
    private final EventProducerService eventProducerService;
    private final OffersCustomerFactoryImpl offersCustomerFactoryImpl;

    public OffersCustomerServiceImpl(final CustomerService customerService,
                                 final OffersCustomerModelService offersCustomerModelService,
                                 final EventProducerService eventProducerService,
                                 final OffersCustomerFactoryImpl offersCustomerFactoryImpl) {
        this.customerService = customerService;
        this.offersCustomerModelService = offersCustomerModelService;
        this.eventProducerService = eventProducerService;
        this.offersCustomerFactoryImpl = offersCustomerFactoryImpl;
    }

    @Override
    public OffersCustomerResponseDTO offers(OffersCustomerRequestDTO offersCustomerRequest) throws JsonProcessingException {
        validateCustomer(offersCustomerRequest);
        OffersCustomerResponseDTO offersCustomerResponse = getOffers(offersCustomerRequest);
        sendEvent(offersCustomerResponse);
        return offersCustomerResponse;
    }


    private void validateCustomer(OffersCustomerRequestDTO offersCustomerRequest) {
        if (this.customerService.findCustomer(offersCustomerRequest.getDocument()).isEmpty()) {
            throw new NotFoundException(String.format(Constants.MSG_NOT_FOUND, offersCustomerRequest.getDocument()));
        }
    }

    private OffersCustomerResponseDTO getOffers(OffersCustomerRequestDTO offersCustomerRequest) {
        return this.offersCustomerFactoryImpl.convertOffersCustomerModelToOffersResponse(
                this.offersCustomerModelService.offers(offersCustomerRequest.getDocument()));
    }

    private void sendEvent(OffersCustomerResponseDTO offersCustomerResponse) throws JsonProcessingException {
        this.eventProducerService.send(
                this.offersCustomerFactoryImpl.convertOffersCustomerModelToString(offersCustomerResponse)
                , TopicEnum.listarProdutos);
    }
}
