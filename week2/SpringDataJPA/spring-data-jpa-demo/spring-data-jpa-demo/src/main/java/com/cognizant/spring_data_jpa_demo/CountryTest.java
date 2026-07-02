package com.cognizant.spring_data_jpa_demo;

import com.cognizant.spring_data_jpa_demo.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CountryTest implements CommandLineRunner {

    @Autowired
    private CountryService service;

    @Override
public void run(String... args) {
    System.out.println(service.searchCountry("Uni"));
}
}