package com.itau.api.renegociation.service.impl;

import com.itau.api.renegociation.constant.Constants;
import com.itau.api.renegociation.model.SimulationModel;
import com.itau.api.renegociation.repository.SimulationModelRepository;
import com.itau.api.renegociation.service.SimulationModelService;
import org.springframework.stereotype.Service;

@Service
public class SimulationModelServiceImpl implements SimulationModelService {

    private final SimulationModelRepository simulationModelRepository;

    public SimulationModelServiceImpl(final SimulationModelRepository simulationModelRepository) {
        this.simulationModelRepository = simulationModelRepository;
    }

    @Override
    public SimulationModel simulation(String document) {
        return simulationModelRepository.save(
                SimulationModel
                        .builder()
                        .documentId(document)
                        .status(Constants.STATUS_PROCESSANDO)
                        .build());
    }
}
