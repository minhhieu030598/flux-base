package com.minhhieu.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan({"com.minhhieu.core", "com.minhhieu.commons"})
@EnableR2dbcRepositories(basePackages = {"com.minhhieu.core", "com.minhhieu.commons"})
public class CoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }


}
