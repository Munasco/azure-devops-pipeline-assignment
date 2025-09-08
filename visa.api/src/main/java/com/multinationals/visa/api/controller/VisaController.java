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

    // Test endpoint with mock data
    @GetMapping("/visas/test")
    public ResponseEntity<List<VisaDTO>> getTestVisas() {
        try {
            List<VisaDTO> mockVisas = new ArrayList<>();
            
            // US Visas
            VisaDTO visa1 = new VisaDTO();
            visa1.setVisaId(1L);
            visa1.setName("Tourist Visa (B-2)");
            visa1.setProcessTimeInDays(14);
            visa1.setFeesLow(160.0);
            visa1.setFeesHigh(160.0);
            visa1.setCountryCode("US");
            visa1.setRegion("North America");
            visa1.setGdpRank(1);
            visa1.setHasRoadToCitizenship(false);
            visa1.setHasPerks(true);
            mockVisas.add(visa1);
            
            VisaDTO visa2 = new VisaDTO();
            visa2.setVisaId(2L);
            visa2.setName("H-1B Work Visa");
            visa2.setProcessTimeInDays(90);
            visa2.setFeesLow(460.0);
            visa2.setFeesHigh(2000.0);
            visa2.setCountryCode("US");
            visa2.setRegion("North America");
            visa2.setGdpRank(1);
            visa2.setHasRoadToCitizenship(true);
            visa2.setHasPerks(true);
            mockVisas.add(visa2);
            
            // Canada Visas
            VisaDTO visa3 = new VisaDTO();
            visa3.setVisaId(3L);
            visa3.setName("Visitor Visa");
            visa3.setProcessTimeInDays(21);
            visa3.setFeesLow(100.0);
            visa3.setFeesHigh(100.0);
            visa3.setCountryCode("CA");
            visa3.setRegion("North America");
            visa3.setGdpRank(10);
            visa3.setHasRoadToCitizenship(false);
            visa3.setHasPerks(true);
            mockVisas.add(visa3);
            
            VisaDTO visa4 = new VisaDTO();
            visa4.setVisaId(4L);
            visa4.setName("Express Entry (PR)");
            visa4.setProcessTimeInDays(180);
            visa4.setFeesLow(1325.0);
            visa4.setFeesHigh(1325.0);
            visa4.setCountryCode("CA");
            visa4.setRegion("North America");
            visa4.setGdpRank(10);
            visa4.setHasRoadToCitizenship(true);
            visa4.setHasPerks(true);
            mockVisas.add(visa4);
            
            // UK Visas
            VisaDTO visa5 = new VisaDTO();
            visa5.setVisaId(5L);
            visa5.setName("Student Visa (Tier 4)");
            visa5.setProcessTimeInDays(21);
            visa5.setFeesLow(348.0);
            visa5.setFeesHigh(475.0);
            visa5.setCountryCode("UK");
            visa5.setRegion("Europe");
            visa5.setGdpRank(5);
            visa5.setHasRoadToCitizenship(false);
            visa5.setHasPerks(false);
            mockVisas.add(visa5);
            
            VisaDTO visa6 = new VisaDTO();
            visa6.setVisaId(6L);
            visa6.setName("Skilled Worker Visa");
            visa6.setProcessTimeInDays(21);
            visa6.setFeesLow(610.0);
            visa6.setFeesHigh(1408.0);
            visa6.setCountryCode("UK");
            visa6.setRegion("Europe");
            visa6.setGdpRank(5);
            visa6.setHasRoadToCitizenship(true);
            visa6.setHasPerks(true);
            mockVisas.add(visa6);
            
            // Australia Visas
            VisaDTO visa7 = new VisaDTO();
            visa7.setVisaId(7L);
            visa7.setName("Tourist Visa (600)");
            visa7.setProcessTimeInDays(7);
            visa7.setFeesLow(145.0);
            visa7.setFeesHigh(365.0);
            visa7.setCountryCode("AU");
            visa7.setRegion("Oceania");
            visa7.setGdpRank(13);
            visa7.setHasRoadToCitizenship(false);
            visa7.setHasPerks(true);
            mockVisas.add(visa7);
            
            VisaDTO visa8 = new VisaDTO();
            visa8.setVisaId(8L);
            visa8.setName("Skilled Independent (189)");
            visa8.setProcessTimeInDays(365);
            visa8.setFeesLow(4045.0);
            visa8.setFeesHigh(4045.0);
            visa8.setCountryCode("AU");
            visa8.setRegion("Oceania");
            visa8.setGdpRank(13);
            visa8.setHasRoadToCitizenship(true);
            visa8.setHasPerks(true);
            mockVisas.add(visa8);
            
            // Germany Visas
            VisaDTO visa9 = new VisaDTO();
            visa9.setVisaId(9L);
            visa9.setName("EU Blue Card");
            visa9.setProcessTimeInDays(90);
            visa9.setFeesLow(100.0);
            visa9.setFeesHigh(100.0);
            visa9.setCountryCode("DE");
            visa9.setRegion("Europe");
            visa9.setGdpRank(4);
            visa9.setHasRoadToCitizenship(true);
            visa9.setHasPerks(true);
            mockVisas.add(visa9);
            
            // Singapore Visas
            VisaDTO visa10 = new VisaDTO();
            visa10.setVisaId(10L);
            visa10.setName("Employment Pass");
            visa10.setProcessTimeInDays(21);
            visa10.setFeesLow(105.0);
            visa10.setFeesHigh(225.0);
            visa10.setCountryCode("SG");
            visa10.setRegion("Asia");
            visa10.setGdpRank(36);
            visa10.setHasRoadToCitizenship(true);
            visa10.setHasPerks(true);
            mockVisas.add(visa10);
            
            return new ResponseEntity<>(mockVisas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}
