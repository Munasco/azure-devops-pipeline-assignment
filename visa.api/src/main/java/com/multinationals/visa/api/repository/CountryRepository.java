package com.multinationals.visa.api.repository;

import com.multinationals.visa.api.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findCountryByCountryCode(String countryCode);
}
