package com.multinationals.visa.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
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

    public String getPerk() {
        return perk;
    }

    public void setPerk(String perk) {
        this.perk = perk;
    }
}
