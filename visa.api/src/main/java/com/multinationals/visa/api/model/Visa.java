package com.multinationals.visa.api.model;

import java.util.List;

import jakarta.persistence.*;



/**
 * Visa
 */
@Entity
public class Visa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long visaId;

    private String name;

    private Integer processTimeInDays;

    private Double feesLow;

    private Double feesHigh;

    @ManyToOne
    @JoinColumn(name = "country_code", referencedColumnName = "countryCode")
    private Country country;

    private String region;

    private Integer gdpRank;

    private Boolean hasPerks;

    private Boolean hasRoadToCitizenship;

    @OneToMany(mappedBy = "visa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VisaDocument> documents;

    @OneToMany(mappedBy = "visa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VisaStep> steps;

    @OneToMany(mappedBy = "visa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VisaRequirement> requirements;

    @OneToMany(mappedBy = "visa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VisaPerk> perks;

    public Visa() {

    }

    public Visa(Long visaId, String name, Integer processTimeInDays, Double feesLow, Double feesHigh, Country country, String region, Integer gdpRank, Boolean hasPerks, Boolean hasRoadToCitizenship, List<VisaDocument> documents, List<VisaStep> steps, List<VisaRequirement> requirements, List<VisaPerk> perks) {
        this.visaId = visaId;
        this.name = name;
        this.processTimeInDays = processTimeInDays;
        this.feesLow = feesLow;
        this.feesHigh = feesHigh;
        this.country = country;
        this.region = region;
        this.gdpRank = gdpRank;
        this.hasPerks = hasPerks;
        this.hasRoadToCitizenship = hasRoadToCitizenship;
        this.documents = documents;
        this.steps = steps;
        this.requirements = requirements;
        this.perks = perks;
    }

    public Long getVisaId() {
        return visaId;
    }

    public void setVisaId(Long visaId) {
        this.visaId = visaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProcessTimeInDays() {
        return processTimeInDays;
    }

    public void setProcessTimeInDays(Integer processTimeInDays) {
        this.processTimeInDays = processTimeInDays;
    }

    public Double getFeesLow() {
        return feesLow;
    }

    public void setFeesLow(Double feesLow) {
        this.feesLow = feesLow;
    }

    public Double getFeesHigh() {
        return feesHigh;
    }

    public void setFeesHigh(Double feesHigh) {
        this.feesHigh = feesHigh;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getGdpRank() {
        return gdpRank;
    }

    public void setGdpRank(Integer gdpRank) {
        this.gdpRank = gdpRank;
    }

    public Boolean getHasPerks() {
        return hasPerks;
    }

    public void setHasPerks(Boolean hasPerks) {
        this.hasPerks = hasPerks;
    }

    public Boolean getHasRoadToCitizenship() {
        return hasRoadToCitizenship;
    }

    public void setHasRoadToCitizenship(Boolean hasRoadToCitizenship) {
        this.hasRoadToCitizenship = hasRoadToCitizenship;
    }

    public List<VisaDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(List<VisaDocument> documents) {
        this.documents = documents;
    }

    public List<VisaStep> getSteps() {
        return steps;
    }

    public void setSteps(List<VisaStep> steps) {
        this.steps = steps;
    }

    public List<VisaRequirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<VisaRequirement> requirements) {
        this.requirements = requirements;
    }

    public List<VisaPerk> getPerks() {
        return perks;
    }

    public void setPerks(List<VisaPerk> perks) {
        this.perks = perks;
    }
}