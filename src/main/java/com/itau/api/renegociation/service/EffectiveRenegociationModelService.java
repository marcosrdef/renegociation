package com.itau.api.renegociation.service;

import com.itau.api.renegociation.model.EffectiveRenegociationModel;

public interface EffectiveRenegociationModelService {
    EffectiveRenegociationModel effective(String document);
}
