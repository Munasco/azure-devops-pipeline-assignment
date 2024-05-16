package com.multinationals.visa.api.model;

import jakarta.persistence.*;


import java.util.List;

@Entity

public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long countryId;

    private String countryName;

    private String countryCode;

    private String dialCode;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Visa> visas;

    public Country() {

    }

    public Country(long countryId, String countryName, String countryCode, String dialCode, List<Visa> visas) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.dialCode = dialCode;
        this.visas = visas;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDialCode() {
        return dialCode;
    }

    public void setDialCode(String dialCode) {
        this.dialCode = dialCode;
    }

    public List<Visa> getVisas() {
        return visas;
    }

    public void setVisas(List<Visa> visas) {
        this.visas = visas;
    }
}
