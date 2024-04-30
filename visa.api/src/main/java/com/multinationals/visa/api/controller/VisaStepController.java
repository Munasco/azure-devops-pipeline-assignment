package com.multinationals.visa.api.controller;

import com.multinationals.visa.api.model.Visa;
import com.multinationals.visa.api.model.VisaStep;
import com.multinationals.visa.api.model.dto.VisaStepDTO;
import com.multinationals.visa.api.repository.VisaRepository;
import com.multinationals.visa.api.repository.VisaStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api")
@CrossOrigin
public class VisaStepController {
    @Autowired
    private VisaStepRepository visaStepRepository;
    @Autowired
    private VisaRepository visaRepository;

    @GetMapping("/visa_steps")
    public ResponseEntity<List<VisaStepDTO>> getAllVisaSteps(@RequestParam(required = false) Integer visa_id) {
        List<VisaStepDTO> visaStepDTOs = new ArrayList<VisaStepDTO>();
        try {
            if (visa_id != 0) {
                Visa visa = visaRepository.findByVisaId(visa_id);
                List<VisaStep> steps = visaStepRepository.findVisaStepsByVisa(visa);
                visaStepDTOs = steps.stream().map(this::mapToDto).collect(Collectors.toList());
                return new ResponseEntity<>(visaStepDTOs, HttpStatus.OK);
            } else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private VisaStepDTO mapToDto(VisaStep visaStep) {
        VisaStepDTO dto = new VisaStepDTO();
        dto.setVisaId(visaStep.getVisa().getVisaId());
        dto.setStepId(visaStep.getStepId());
        dto.setStep(visaStep.getStep());
        return dto;
    }

}
