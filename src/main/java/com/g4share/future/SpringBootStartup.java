package com.g4share.future;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching
@EnableAsync
@ComponentScan("com.g4share.future")
public class SpringBootStartup {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootStartup.class, args);
    }
}