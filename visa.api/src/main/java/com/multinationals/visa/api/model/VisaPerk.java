package com.multinationals.visa.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class VisaPerk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "perk_id")
    private long perkId;

    @ManyToOne
    @JoinColumn(name="visa_id")
    private Visa visa;

    @Column(name = "perk")
    private String perk;

    public VisaPerk() {

    }

    public VisaPerk(long perkId, Visa visa, String perk) {
        this.perkId = perkId;
        this.visa = visa;
        this.perk = perk;
    }

    public long getPerkId() {
        return perkId;
    }

    public void setPerkId(long perkId) {
        this.perkId = perkId;
    }

    public Visa getVisa() {
        return visa;
    }

    public void setVisa(Visa visa) {
        this.visa = visa;
    }

    public String getPerk() {
        return perk;
    }

    public void setPerk(String perk) {
        this.perk = perk;
    }
}
