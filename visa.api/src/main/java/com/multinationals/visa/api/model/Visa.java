package com.multinationals.visa.api.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Visa
 */
@Data
@Entity
@AllArgsConstructor
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
}