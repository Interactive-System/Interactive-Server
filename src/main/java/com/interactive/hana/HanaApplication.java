package com.interactive.hana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class HanaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HanaApplication.class, args);
    }

}
