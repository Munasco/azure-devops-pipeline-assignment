package com.multinationals.visa.api.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Visa
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Visa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long visaId;

    @Column(name = "process_time_in_weeks")
    private int processTimeInWeeks;

    @Column(name = "fees")
    private int fees;

    @Column(name = "country")
    private String country;

    @Column(name = "region")
    private String region;

    @Column(name = "gdp_rank")
    private int gdpRank;

    @OneToMany(mappedBy = "visa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VisaDocument> documents;

    @OneToMany(mappedBy = "visa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VisaStep> steps;

    @OneToMany(mappedBy = "visa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VisaRequirement> requirements;

    @OneToMany(mappedBy = "visa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VisaPerk> perks;


    public int getFees() {
        return fees;
    }

    public int getProcessTimeInWeeks() {
        return processTimeInWeeks;
    }

    public int getGdpRank() {
        return gdpRank;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }

    public void setProcessTimeInWeeks(int processTimeInWeeks) {
        this.processTimeInWeeks = processTimeInWeeks;
    }

    public void setGdpRank(int gdpRank) {
        this.gdpRank = gdpRank;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}