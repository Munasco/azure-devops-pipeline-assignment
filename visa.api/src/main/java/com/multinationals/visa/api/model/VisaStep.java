package com.multinationals.visa.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * VisaSteps
 */
@Entity
public class VisaStep {
    public VisaStep(long stepId, Visa visa, String step) {
        this.stepId = stepId;
        this.visa = visa;
        this.step = step;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long stepId;

    @ManyToOne
    @JoinColumn(name="visa_id")
    private Visa visa;

    private String step;

    public VisaStep() {

    }

    public long getStepId() {
        return stepId;
    }

    public void setStepId(long stepId) {
        this.stepId = stepId;
    }

    public Visa getVisa() {
        return visa;
    }

    public void setVisa(Visa visa) {
        this.visa = visa;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }
}