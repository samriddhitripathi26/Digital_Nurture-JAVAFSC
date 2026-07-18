package com.cognizant.spring_data_jpa_demo.service;

import com.cognizant.spring_data_jpa_demo.model.Country;
import com.cognizant.spring_data_jpa_demo.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository repo;

    public List<Country> searchCountry(String text) {
    return repo.findByNameContainingIgnoreCase(text);
}

    public Country getCountryUsingCode(String code) {
        return repo.findByCode(code);
    }
    public void updateCountry() {
    Country c = repo.findById("IN").orElse(null);
    if (c != null) {
        c.setName("Bharat");
        repo.save(c);
    }
}
}