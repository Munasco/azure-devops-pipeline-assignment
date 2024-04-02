package com.multinationals.visa.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multinationals.visa.api.model.Visa;
import com.multinationals.visa.api.repository.VisaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



/**
 * MainController
 */
@RestController
@RequestMapping(path="/api")
public class MainController {

    @Autowired
    private VisaRepository visaRepository;

    @GetMapping("/visas")
    public ResponseEntity<List<Visa>> getAllVisas(@RequestParam(required = false) String country) {
        try {
            List<Visa> visas = new ArrayList<Visa>();

            if (country == null)
                visaRepository.findAll().forEach(visas::add);
            else
                visaRepository.findByCountry(country);
            if (visas.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(visas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @PostMapping("/visas")
    public ResponseEntity<Visa> createVisa(@RequestBody Visa visa) {
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
    
    
}