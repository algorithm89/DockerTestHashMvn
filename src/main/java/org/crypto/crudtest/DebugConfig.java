package org.crypto.crudtest;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DebugConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @PostConstruct
    public void printConfig() {
        System.out.println("=== DB CONFIG DEBUG ===");
        System.out.println("DB URL  = " + url);
        System.out.println("DB USER = " + username);
        System.out.println("DB PASS = " + password);
        System.out.println("=======================");
    }
}

