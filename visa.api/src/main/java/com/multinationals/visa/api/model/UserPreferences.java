package com.multinationals.visa.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserPreferences
 */
@Data
@Entity
@AllArgsConstructor
public class UserPreferences {
    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "country_of_origin")
    private String countryOfOrigin;

    @Column(name = "country_of_residency")
    private String countryOfResidency;

    @Column(name = "passport_is_valid")
    private boolean passportIsValid;

    @Column(name = "has_criminal_record")
    private boolean hasCriminalRecord;

    @Column(name = "has_proof_of_funds")
    private boolean hasProofOfFunds;

    @Column(name = "has_health_insurance")
    private boolean hasHealthInsurance;

    @Column(name = "language_proficency")
    private int languageProficency;

    public UserPreferences() {

    }
}