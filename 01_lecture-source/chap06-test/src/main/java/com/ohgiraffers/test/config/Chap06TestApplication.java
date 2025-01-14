package com.ohgiraffers.test.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.ohgiraffers.test")
@EnableJpaRepositories(basePackages = "com.ohgiraffers.test")
public class Chap06TestApplication {


    public static void main(String[] args) {
        SpringApplication.run(Chap06TestApplication.class, args);
    }

}
