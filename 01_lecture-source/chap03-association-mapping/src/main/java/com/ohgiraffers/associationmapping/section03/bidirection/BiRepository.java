package com.ohgiraffers.associationmapping.section03.bidirection;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BiRepository {

    @Autowired
    private EntityManager manager;

    public Menu find(int menuCode) {
        return manager.find(Menu.class, menuCode);
    }
}
