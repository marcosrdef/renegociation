package com.itau.api.renegociation.repository;

import com.itau.api.renegociation.model.SimulationModel;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface SimulationModelRepository extends CrudRepository<SimulationModel, String> {
    Optional<SimulationModel> findByDocumentId(String documentId);
}
