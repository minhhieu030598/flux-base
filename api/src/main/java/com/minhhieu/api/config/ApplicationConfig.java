package com.minhhieu.api.config;

import com.minhhieu.commons.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Configuration
public class ApplicationConfig {

    @Bean
    public JwtService jwtService(@Value("${authentication.jwt.public-key}") String publicKey,
                                 @Value("${authentication.jwt.private-key}") String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return new JwtService(publicKey, privateKey);
    }

}
