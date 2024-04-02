package com.multinationals.visa.api.model;



import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User
 */
@Data
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

    public void setDateOfBirth(long dobUnix) {
        Instant dateInstance = Instant.ofEpochMilli(dobUnix);
        this.dateOfBirth = dateInstance.atZone(ZoneOffset.UTC).toLocalDate();
    }
}