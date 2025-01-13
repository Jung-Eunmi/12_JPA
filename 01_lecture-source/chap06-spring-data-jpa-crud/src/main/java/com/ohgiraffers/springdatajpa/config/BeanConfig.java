package com.ohgiraffers.springdatajpa.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// config 폴더에 Application 을 넣어줘서 Bean을 Scan 하는 Configuration 을 만들어준것이다.
@Configuration
@ComponentScan(basePackages = "com.ohgiraffers.springdatajpa")
public class BeanConfig {

}
