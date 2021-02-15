package com.itau.api.renegociation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.api.renegociation.dto.*;
import com.itau.api.renegociation.enums.TopicEnum;
import com.itau.api.renegociation.service.EventProducerService;
import com.itau.api.renegociation.service.OffersCustomerService;
import com.itau.api.renegociation.service.RenegociationService;
import com.itau.api.renegociation.service.SimulationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RenegociationController {

    private final OffersCustomerService offersCustomerService;
    private final RenegociationService renegociationService;
    private final SimulationService simulationService;

    public RenegociationController(final OffersCustomerService offersCustomerService,
                                   final RenegociationService renegociationService,
                                   final SimulationService simulationService) {
        this.offersCustomerService = offersCustomerService;
        this.renegociationService = renegociationService;
        this.simulationService = simulationService;
    }

    @PostMapping("/simulation")
    public ResponseEntity<SimulationResponseDTO> simulation(@RequestBody @Valid SimulationRequestDTO simulationRequest) throws JsonProcessingException {
        return ResponseEntity.ok(simulationService.simulation(simulationRequest));
    }

    @PostMapping("/renegociation")
    public ResponseEntity<EffectiveResponseDTO> renegociation(@RequestBody @Valid EffectiveRequestDTO effectiveRequest) throws JsonProcessingException {
        return ResponseEntity.ok(renegociationService.renegociation(effectiveRequest));
    }

    @PostMapping("/offers")
    public ResponseEntity<OffersCustomerResponseDTO> offers(@RequestBody @Valid OffersCustomerRequestDTO offersCustomerRequest) throws JsonProcessingException {
        return ResponseEntity.ok(offersCustomerService.offers(offersCustomerRequest));
    }
}
