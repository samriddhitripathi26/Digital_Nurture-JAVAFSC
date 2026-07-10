package com.cognizant.springrest.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Country {
    
    @NotBlank(message = "Country code is required")
    @Size(min = 2, max = 2, message = "Country code must be exactly 2 characters")
    private String code;

    @NotBlank(message = "Country name is required")
    @Size(min = 2, max = 50, message = "Country name must be between 2 and 50 characters")
    private String name;

    public Country() {
    }

    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country{code='" + code + "', name='" + name + "'}";
    }
}
