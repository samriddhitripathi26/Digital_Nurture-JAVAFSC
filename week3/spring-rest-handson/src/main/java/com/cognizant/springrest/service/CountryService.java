package com.cognizant.springrest.service;

import org.springframework.stereotype.Service;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.cognizant.springrest.model.Country;
import java.util.List;
import java.util.ArrayList;

@Service
public class CountryService {

    private List<Country> countries;

    public CountryService() {
        countries = new ArrayList<>();
        countries.add(new Country("IN", "India"));
        countries.add(new Country("US", "United States"));
        countries.add(new Country("JP", "Japan"));
        countries.add(new Country("AU", "Australia"));
    }

    public Country getCountry() {
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        return context.getBean("country", Country.class);
    }

    public Country getCountry(String code) {
        for (Country country : countries) {
            if (country.getCode().equalsIgnoreCase(code)) {
                return country;
            }
        }
        return null;
    }

    public Country addCountry(Country country) {
        countries.add(country);
        return country;
    }

    public Country updateCountry(String code, Country updatedCountry) {
        Country existingCountry = getCountry(code);
        if (existingCountry != null) {
            existingCountry.setName(updatedCountry.getName());
            return existingCountry;
        }
        return null;
    }

    public boolean deleteCountry(String code) {
        Country existingCountry = getCountry(code);
        if (existingCountry != null) {
            countries.remove(existingCountry);
            return true;
        }
        return false;
    }
}
