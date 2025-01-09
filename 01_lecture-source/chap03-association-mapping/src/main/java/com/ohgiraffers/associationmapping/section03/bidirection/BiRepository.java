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

    public Category findCategory(int categoryCode) {
        return manager.find(Category.class, categoryCode);
    }

    public void save(Menu newMenu) {
        manager.persist(newMenu);
    }


    public void saveCategory(Category category) {
        manager.persist(category);
    }
}
