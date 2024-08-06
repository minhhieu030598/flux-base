package com.minhhieu.core.config;

import com.minhhieu.commons.security.XAuthFilter;
import com.minhhieu.commons.security.XAuthDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public XAuthDecoder xAuthenticationDecoder() {
        return new XAuthDecoder();
    }

    @Bean
    public XAuthFilter xAuthFilter(XAuthDecoder xAuthDecoder) {
        return new XAuthFilter(xAuthDecoder);
    }
}
