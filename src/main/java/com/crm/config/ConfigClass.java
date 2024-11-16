package com.crm.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigClass {
    // Create a ModelMapper bean to map entities and DTOs
    //configuration class automatically run when project will start
       @Bean
    public ModelMapper getmapper() {
        return new ModelMapper();
    }

}

