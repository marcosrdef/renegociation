package com.itau.api.renegociation.repository;

import com.itau.api.renegociation.model.EffectiveRenegociationModel;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface EffectiveRenegociationModelRepository extends CrudRepository<EffectiveRenegociationModel, String> {
    Optional<EffectiveRenegociationModel> findByDocumentId(String documentId);
}

