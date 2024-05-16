package com.multinationals.visa.api.model.dto;


public class VisaStepDTO {
    private long stepId;
    private long visaId;
    private String step;

    public long getStepId() {
        return stepId;
    }

    public void setStepId(long stepId) {
        this.stepId = stepId;
    }

    public long getVisaId() {
        return visaId;
    }

    public void setVisaId(long visaId) {
        this.visaId = visaId;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }
}
