package com.cognizant.spring_data_jpa_demo.repository;

import com.cognizant.spring_data_jpa_demo.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CountryRepository
        extends JpaRepository<Country, String> {

    Country findByCode(String code);

List<Country> findAllByOrderByNameAsc();

List<Country> findByNameContainingIgnoreCase(String text);
}