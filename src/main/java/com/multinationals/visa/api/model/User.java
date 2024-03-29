package com.multinationals.visa.api.model;



import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * User
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class User {
    @Id
    @Column(name = "email")
    private String email;
    
    @Column(name = "government_name")
    private String governmentName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    
    @Column(name = "country")
    private String country;

    public String getCountry() {
        return country;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getGovernmentName() {
        return governmentName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDateOfBirth(long dobUnix) {
        Instant dateInstance = Instant.ofEpochMilli(dobUnix);
        this.dateOfBirth = dateInstance.atZone(ZoneOffset.UTC).toLocalDate();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGovernmentName(String governmentName) {
        this.governmentName = governmentName;
    }
}