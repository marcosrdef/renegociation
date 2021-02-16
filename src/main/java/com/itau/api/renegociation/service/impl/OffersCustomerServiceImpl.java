package com.itau.api.renegociation.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.api.renegociation.constant.Constants;
import com.itau.api.renegociation.dto.OffersCustomerRequestDTO;
import com.itau.api.renegociation.dto.OffersCustomerResponseDTO;
import com.itau.api.renegociation.enums.TopicEnum;
import com.itau.api.renegociation.exception.NotFoundException;
import com.itau.api.renegociation.factory.impl.OffersCustomerFactoryImpl;
import com.itau.api.renegociation.model.CustomerModel;
import com.itau.api.renegociation.service.CustomerService;
import com.itau.api.renegociation.service.EventProducerService;
import com.itau.api.renegociation.service.OffersCustomerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OffersCustomerServiceImpl implements OffersCustomerService {

    private final CustomerService customerService;
    private final EventProducerService eventProducerService;
    private final OffersCustomerFactoryImpl offersCustomerFactoryImpl;

    public OffersCustomerServiceImpl(final CustomerService customerService,
                                 final EventProducerService eventProducerService,
                                 final OffersCustomerFactoryImpl offersCustomerFactoryImpl) {
        this.customerService = customerService;
        this.eventProducerService = eventProducerService;
        this.offersCustomerFactoryImpl = offersCustomerFactoryImpl;
    }

    @Override
    public OffersCustomerResponseDTO offers(OffersCustomerRequestDTO offersCustomerRequest) throws JsonProcessingException {
        OffersCustomerResponseDTO offersCustomerResponse = getOffers(validateCustomer(offersCustomerRequest));
        sendEvent(offersCustomerResponse);
        return offersCustomerResponse;
    }


    private CustomerModel validateCustomer(OffersCustomerRequestDTO offersCustomerRequest) {
        Optional<CustomerModel> customer = this.customerService.findCustomer(offersCustomerRequest.getDocument());
        if (customer.isEmpty()) {
            throw new NotFoundException(String.format(Constants.MSG_NOT_FOUND, offersCustomerRequest.getDocument()));
        }
        return customer.get();
    }

    private OffersCustomerResponseDTO getOffers(CustomerModel customerModel) {
        return this.offersCustomerFactoryImpl.convertOffersCustomerModelToOffersResponse(customerModel);
    }

    private void sendEvent(OffersCustomerResponseDTO offersCustomerResponse) throws JsonProcessingException {
        this.eventProducerService.send(
                this.offersCustomerFactoryImpl.convertOffersCustomerModelToString(offersCustomerResponse)
                , TopicEnum.listarProdutos);
    }
}
