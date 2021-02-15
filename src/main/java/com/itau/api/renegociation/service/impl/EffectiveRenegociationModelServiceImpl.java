package com.itau.api.renegociation.service.impl;

import com.itau.api.renegociation.constant.Constants;
import com.itau.api.renegociation.model.EffectiveRenegociationModel;
import com.itau.api.renegociation.repository.EffectiveRenegociationModelRepository;
import com.itau.api.renegociation.service.EffectiveRenegociationModelService;
import org.springframework.stereotype.Service;

@Service
public class EffectiveRenegociationModelServiceImpl implements EffectiveRenegociationModelService {

    private final EffectiveRenegociationModelRepository effectiveRenegociationModelRepository;

    public EffectiveRenegociationModelServiceImpl(final EffectiveRenegociationModelRepository effectiveRenegociationModelRepository) {
        this.effectiveRenegociationModelRepository = effectiveRenegociationModelRepository;
    }
    @Override
    public EffectiveRenegociationModel effective(String document) {
        return this.effectiveRenegociationModelRepository.save(
                EffectiveRenegociationModel.builder()
                .documentId(document)
                .status(Constants.STATUS_PROCESSANDO)
                .build());
    }
}
