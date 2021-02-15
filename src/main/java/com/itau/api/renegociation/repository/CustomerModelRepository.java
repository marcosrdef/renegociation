package com.itau.api.renegociation.repository;

import com.itau.api.renegociation.model.CustomerModel;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
@EnableScan
public interface CustomerModelRepository  extends CrudRepository<CustomerModel, String> {
    Optional<CustomerModel> findByDocumentId(String documentId);
}
