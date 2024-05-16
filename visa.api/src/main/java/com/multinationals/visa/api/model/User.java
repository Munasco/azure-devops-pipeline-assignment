package com.multinationals.visa.api.model;



import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

// import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


/**
 * User
 */
@Entity
public class User {
    @Id
    private String email;

    private String governmentName;

    private LocalDate dateOfBirth;

    private String country;

    public User() {

    }

    public User(String email, String governmentName, LocalDate dateOfBirth, String country) {
        this.email = email;
        this.governmentName = governmentName;
        this.dateOfBirth = dateOfBirth;
        this.country = country;
    }

    public void setDateOfBirth(long dobUnix) {
        Instant dateInstance = Instant.ofEpochMilli(dobUnix);
        this.dateOfBirth = dateInstance.atZone(ZoneOffset.UTC).toLocalDate();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGovernmentName() {
        return governmentName;
    }

    public void setGovernmentName(String governmentName) {
        this.governmentName = governmentName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}