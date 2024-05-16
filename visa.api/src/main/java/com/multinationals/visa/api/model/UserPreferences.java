package com.multinationals.visa.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * UserPreferences
 */
@Entity
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

    public UserPreferences(String email, String countryOfOrigin, String countryOfResidency, boolean passportIsValid, boolean hasCriminalRecord, boolean hasProofOfFunds, boolean hasHealthInsurance, int languageProficency) {
        this.email = email;
        this.countryOfOrigin = countryOfOrigin;
        this.countryOfResidency = countryOfResidency;
        this.passportIsValid = passportIsValid;
        this.hasCriminalRecord = hasCriminalRecord;
        this.hasProofOfFunds = hasProofOfFunds;
        this.hasHealthInsurance = hasHealthInsurance;
        this.languageProficency = languageProficency;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getCountryOfResidency() {
        return countryOfResidency;
    }

    public void setCountryOfResidency(String countryOfResidency) {
        this.countryOfResidency = countryOfResidency;
    }

    public boolean isPassportIsValid() {
        return passportIsValid;
    }

    public void setPassportIsValid(boolean passportIsValid) {
        this.passportIsValid = passportIsValid;
    }

    public boolean isHasCriminalRecord() {
        return hasCriminalRecord;
    }

    public void setHasCriminalRecord(boolean hasCriminalRecord) {
        this.hasCriminalRecord = hasCriminalRecord;
    }

    public boolean isHasProofOfFunds() {
        return hasProofOfFunds;
    }

    public void setHasProofOfFunds(boolean hasProofOfFunds) {
        this.hasProofOfFunds = hasProofOfFunds;
    }

    public boolean isHasHealthInsurance() {
        return hasHealthInsurance;
    }

    public void setHasHealthInsurance(boolean hasHealthInsurance) {
        this.hasHealthInsurance = hasHealthInsurance;
    }

    public int getLanguageProficency() {
        return languageProficency;
    }

    public void setLanguageProficency(int languageProficency) {
        this.languageProficency = languageProficency;
    }
}