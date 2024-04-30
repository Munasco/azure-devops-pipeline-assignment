package com.multinationals.visa.api.repository;

import java.util.List;

import com.multinationals.visa.api.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import com.multinationals.visa.api.model.Visa;

public interface VisaRepository extends JpaRepository<Visa, Long>{
    // List<Visa>  
    List<Visa> findByFeesLessThanOrderByGdpRankAsc(double fees);
    List<Visa> findByCountry(Country country);
    List<Visa> findByCountryAndFeesLessThanOrderByGdpRankAsc(Country country, double fees);

    List<Visa> findByRegionAndFeesLessThanOrderByGdpRankAsc(String region, double fees);
    List<Visa> findByRegionAndFeesLessThanAndProcessTimeInDaysLessThanAndGdpRankBetweenOrderByGdpRankAsc(String region, double fees, int processTime, int lowGdp, int highGdp);
    List<Visa> findByRegionOrderByGdpRankAsc(String region);
    Visa findByVisaId(long visaId);
}
