package com.ohgiraffers.section01.entitymanager;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryGenerator {

    private static EntityManagerFactory factory
            = Persistence.createEntityManagerFactory("jpatest");

    // 외부에서 인스턴스 생성 못하게 private 로 접근 제한
    private EntityManagerFactoryGenerator() {}

    public static EntityManagerFactory getInstance() {return factory;}

}
