package com.multinationals.visa.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.multinationals.visa.api.model.Country;
import com.multinationals.visa.api.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.multinationals.visa.api.model.Visa;
import com.multinationals.visa.api.model.dto.VisaDTO;
import com.multinationals.visa.api.repository.VisaRepository;


/**
 * VisaCountroller
 */
@CrossOrigin
@RestController
@RequestMapping(path="/api")

public class VisaController {

    @Autowired
    private VisaRepository visaRepository;

    @Autowired
    private CountryRepository countryRepository;

    @CrossOrigin
    @GetMapping("/visas")
    public ResponseEntity<List<VisaDTO>> getAllVisas(@RequestParam(required = false) String country_code,
    @RequestParam(required = false) Double fees,
    @RequestParam(required = false) String region,
    @RequestParam(required = false) Integer process_time_in_days,
    @RequestParam(required = false) Integer low_gdp,
    @RequestParam(required = false) Integer high_gdp
    )
    {
        
        List<VisaDTO> visaDTOs = new ArrayList<VisaDTO>();
        try {
            List<Visa> visas;
            if (region != null && fees != null && process_time_in_days != null && low_gdp != null && high_gdp != null) {
                visas = visaRepository.findByRegionAndFeesLessThanAndProcessTimeInDaysLessThanAndGdpRankBetweenOrderByGdpRankAsc(region, fees, process_time_in_days, low_gdp, high_gdp);
            }
            else if (country_code != null && fees != null) {
                Country searchCountry = countryRepository.findCountryByCountryCode(country_code);
                visas = visaRepository.findByCountryAndFeesLessThanOrderByGdpRankAsc(searchCountry, fees);
            } else if (region != null && fees  != null) {
                visas = visaRepository.findByRegionAndFeesLessThanOrderByGdpRankAsc(region, fees);
            } else if (country_code != null) {
                Country searchCountry = countryRepository.findCountryByCountryCode(country_code);
                visas = visaRepository.findByCountry(searchCountry);
            } else if (fees != null) {
                visas = visaRepository.findByFeesLessThanOrderByGdpRankAsc(fees);
            } else if (region != null) {
                visas = visaRepository.findByRegionOrderByGdpRankAsc(region);
            } else {
                visas = visaRepository.findAll();
            }
            if (visas.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            visaDTOs = visas.stream().map(this::mapToDto).collect(Collectors.toList());
            return new ResponseEntity<>(visaDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private VisaDTO mapToDto(Visa visa) {
        VisaDTO dto = new VisaDTO();
        dto.setVisaId(visa.getVisaId());
        dto.setName(visa.getName());
        dto.setProcessTimeInDays(visa.getProcessTimeInDays());
        dto.setFees(visa.getFees());
        dto.setCountryCode(visa.getCountry().getCountryCode());
        dto.setRegion(visa.getRegion());
        dto.setGdpRank(visa.getGdpRank());
        dto.setHasRoadToCitizenship(visa.getHasRoadToCitizenship());
        dto.setHasPerks(visa.getHasPerks());
        return dto;
    }

    @PostMapping("/visas")
    public ResponseEntity<Visa> createItem(@RequestBody Visa visa) {
        try {
            Visa savedVisa = visaRepository.save(visa);
            return new ResponseEntity<>(savedVisa, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/visas/{id}")
    public ResponseEntity<Visa> getVisaById(@RequestParam("id") long visaId) {
        Optional<Visa> visaData = visaRepository.findById(visaId);

        if(visaData.isPresent())
            return new ResponseEntity<>(visaData.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/visas/{id}")
    public ResponseEntity<HttpStatus> deleteVisa(@PathVariable("id") long id) {
        try {
            visaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/visas")
    public ResponseEntity<HttpStatus> deleteAllVisa() {
        try {
            visaRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}