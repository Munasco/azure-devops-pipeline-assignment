package com.multinationals.visa.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<VisaDTO>> getAllVisas(
    @RequestParam(required = false) Double fees,
    @RequestParam(required = false) String region,
    @RequestParam(required = false) Integer process_time_in_days,
    @RequestParam(required = false) Integer low_gdp,
    @RequestParam(required = false) Integer high_gdp,
    @RequestParam(required = false) boolean wants_perks,
    @RequestParam(required = false) boolean wants_citizenship
    )
    {

        List<VisaDTO> visaDTOs = new ArrayList<VisaDTO>();
        try {
            List<Visa> visas;
            if (region == null) {
                if (wants_citizenship && wants_perks) {
                    visas = visaRepository.findByAllParametersExceptRegion(fees, process_time_in_days, low_gdp, high_gdp, true, true);

                } else if (wants_citizenship) {
                    visas = visaRepository.findByAllParametersExceptRegionAndPerks(fees, process_time_in_days, low_gdp, high_gdp, true);
                } else if (wants_perks) {
                    visas = visaRepository.findByAllParametersExceptRegionAndCitizenship(fees, process_time_in_days, low_gdp, high_gdp, true);
                } else {
                    visas = visaRepository.findByAllParametersExceptRegionAndCitizenshipAndPerks(fees, process_time_in_days, low_gdp, high_gdp);
                }
            } else {
                if (!wants_citizenship && !wants_perks) {
                    visas = visaRepository.findByAllParametersExceptCitizenshipAndPerks(region, fees, process_time_in_days, low_gdp, high_gdp);
                } else if (!wants_citizenship) {
                    visas = visaRepository.findByAllParametersExceptPerks(region, fees, process_time_in_days, low_gdp, high_gdp, true);
                } else if (!wants_perks) {
                    visas = visaRepository.findByAllParametersExceptCitizenship(region, fees, process_time_in_days, low_gdp, high_gdp, true);
                } else {
                    visas = visaRepository.findByAllParameters(region, fees, process_time_in_days, low_gdp, high_gdp, true, true);
                    System.out.println("citizen and perk");
                }
            }
            if (visas.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            visaDTOs = visas.stream().map(this::mapToDto).collect(Collectors.toList());
            return new ResponseEntity<>(visaDTOs, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("INTERNAL_SERVER_ERROR:");
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    private VisaDTO mapToDto(Visa visa) {
        VisaDTO dto = new VisaDTO();
        dto.setVisaId(visa.getVisaId());
        dto.setName(visa.getName());
        dto.setProcessTimeInDays(visa.getProcessTimeInDays());
        dto.setFeesLow(visa.getFeesLow());
        dto.setFeesHigh(visa.getFeesHigh());
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