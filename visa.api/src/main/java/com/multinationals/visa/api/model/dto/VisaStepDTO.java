package com.multinationals.visa.api.model.dto;

import lombok.Data;

@Data
public class VisaStepDTO {
    private long stepId;
    private long visaId;
    private String step;
}
