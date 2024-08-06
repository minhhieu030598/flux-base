package com.minhhieu.commons.config;

import com.minhhieu.commons.thirdparty.PersonClient;
import com.minhhieu.commons.thirdparty.impl.PersonClientImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public PersonClient personClient(@Value("${person-manager.http.baseUrl}") String baseUrl) {
        return new PersonClientImpl(baseUrl);
    }

}
