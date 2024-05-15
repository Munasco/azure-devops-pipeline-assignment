package com.multinationals.visa.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * VisaSteps
 */
@Data
@Entity
@AllArgsConstructor
public class VisaStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long stepId;

    @ManyToOne
    @JoinColumn(name="visa_id")
    private Visa visa;

    private String step;

    public VisaStep() {

    }
}