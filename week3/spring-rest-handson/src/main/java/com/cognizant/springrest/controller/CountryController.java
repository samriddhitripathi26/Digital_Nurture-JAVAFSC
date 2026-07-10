package com.cognizant.springrest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import com.cognizant.springrest.model.Country;
import com.cognizant.springrest.service.CountryService;
import com.cognizant.springrest.exception.CountryNotFoundException;

@RestController
public class CountryController {

    private static final Logger logger = LoggerFactory.getLogger(CountryController.class);
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/country")
    public Country getCountry() {
        logger.info("GET /country request received");
        return countryService.getCountry();
    }

    @GetMapping("/country/{code}")
    public Country getCountryByCode(@PathVariable String code) {
        logger.info("GET /country/{} request received", code);
        Country country = countryService.getCountry(code);
        if (country == null) {
            throw new CountryNotFoundException("Country not found");
        }
        return country;
    }

    @PostMapping("/country")
    @ResponseStatus(HttpStatus.CREATED)
    public Country addCountry(@Valid @RequestBody Country country) {
        logger.info("POST /country request received: {}", country);
        return countryService.addCountry(country);
    }

    @PutMapping("/country/{code}")
    public Country updateCountry(@PathVariable String code, @Valid @RequestBody Country country) {
        logger.info("PUT /country/{} request received: {}", code, country);
        Country updated = countryService.updateCountry(code, country);
        if (updated == null) {
            throw new CountryNotFoundException("Country not found");
        }
        return updated;
    }

    @DeleteMapping("/country/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCountry(@PathVariable String code) {
        logger.info("DELETE /country/{} request received", code);
        boolean deleted = countryService.deleteCountry(code);
        if (!deleted) {
            throw new CountryNotFoundException("Country not found");
        }
    }
}
