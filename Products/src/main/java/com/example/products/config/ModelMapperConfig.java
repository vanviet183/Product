package com.example.products.config;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//never name modelMapper duplicate class
@Configuration
public class ModelMapperConfig {
@Bean
 public ModelMapper modelMapper(){
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().
            setMatchingStrategy(MatchingStrategies.STRICT)
            .setSkipNullEnabled(true);
    return modelMapper;
    }
}
