package com.ohgiraffers.springdatajpa.config;

import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
// Application 을 다른 패키지로 이동했을때는 설정을 해주어야한다.
@EnableJpaRepositories(basePackages = "com.ohgiraffers.springdatajpa")
@EntityScan(basePackages = "com.ohgiraffers.springdatajpa")
public class Chap06SpringDataJpaCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chap06SpringDataJpaCrudApplication.class, args);
    }

}
