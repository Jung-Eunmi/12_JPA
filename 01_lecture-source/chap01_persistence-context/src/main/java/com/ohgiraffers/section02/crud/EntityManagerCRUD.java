package com.ohgiraffers.section02.crud;

import com.ohgiraffers.section01.entitymanager.EntityManagerGenerator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class EntityManagerCRUD {

    private EntityManager manager;

    public EntityManager getManagerInstance() {
        return manager;
    }

    public Menu findMenuByMenuCode(int menuCode) {

        manager = EntityManagerGenerator.getInstance();

        return manager.find(Menu.class, menuCode);
    }

    public Long saveAndReturnCount(Menu newMenu) {

        manager = EntityManagerGenerator.getInstance();
        EntityTransaction transaction = manager.getTransaction();
        // 시작
        transaction.begin();
        // 등록
        manager.persist(newMenu);
        // 등록 한 엔티티를 반영하라고 명령
        manager.flush();
        // DB 에 저장
        transaction.commit();

        return getCount(manager);
    }

    private Long getCount(EntityManager manager) {
        return manager.createQuery("SELECT COUNT(*) FROM section02Menu", Long.class).getSingleResult();
    }

    public Menu modifyMenuName(int code, String name) {

        // 이름을 바꿀 메뉴를 특정
        Menu foundMenu = findMenuByMenuCode(code);
        System.out.println("foundMenu = " + foundMenu);

        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        foundMenu.setMenuName(name);

        transaction.commit();

        return foundMenu;
    }

    public Long removeAndReturnCount(int code) {

        // 메뉴 특정
        Menu foundMenu = findMenuByMenuCode(code);

        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();

        manager.remove(foundMenu);

        transaction.commit();

        return getCount(manager);
    }
}
