package com.itau.api.renegociation.repository;

import com.itau.api.renegociation.model.OffersCustomerModel;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface OffersCustomerModelRepository extends CrudRepository<OffersCustomerModel, String> {
    Optional<OffersCustomerModel> findByDocumentId(String documentId);
}
