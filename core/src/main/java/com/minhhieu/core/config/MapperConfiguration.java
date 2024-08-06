package com.minhhieu.core.config;

import com.minhhieu.core.mapper.PersonMapper;
import com.minhhieu.core.mapper.PersonMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {
    @Bean
    public PersonMapper personMapper() {
        return new PersonMapperImpl();
    }
}
