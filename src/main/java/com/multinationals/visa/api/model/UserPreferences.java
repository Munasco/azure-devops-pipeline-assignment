package com.multinationals.visa.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * UserPreferences
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
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

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public String getCountryOfResidency() {
        return countryOfResidency;
    }

    public String getEmail() {
        return email;
    }
    
    public boolean getPassportIsValid() {
        return passportIsValid;
    }

    public boolean getHasCriminalRecord() {
        return hasCriminalRecord;
    }

    public boolean getHasHealthInsurance() {
        return hasHealthInsurance;
    }

    public boolean getHasProofOfFunds() {
        return hasProofOfFunds;
    }

    public int getLanguageProficency() {
        return languageProficency;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public void setCountryOfResidency(String countryOfResidency) {
        this.countryOfResidency = countryOfResidency;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setHasCriminalRecord(boolean hasCriminalRecord) {
        this.hasCriminalRecord = hasCriminalRecord;
    }

    public void setHasHealthInsurance(boolean hasHealthInsurance) {
        this.hasHealthInsurance = hasHealthInsurance;
    }

    public void setHasProofOfFunds(boolean hasProofOfFunds) {
        this.hasProofOfFunds = hasProofOfFunds;
    }

    public void setLanguageProficency(int languageProficency) {
        this.languageProficency = languageProficency;
    }

    public void setPassportIsValid(boolean passportIsValid) {
        this.passportIsValid = passportIsValid;
    }

    

}