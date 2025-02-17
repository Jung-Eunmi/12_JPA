package com.ohgiraffers.section01.entitymanager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EntityManagerGeneratorTest {

    /* title. Persistence Context 를 이해하기 위한 엔티티 매니저와 팩토리 */

    /* comment.
     *   엔티티 매니저 팩토리란?
     *   엔티티 매니저를 생성할 수 있는 기능을 제공하는 클래스이다.
     *   팩토리를 thread 에 안전하기 때문에(동시성) 하나의 인스턴스를 생성해서
     *   Application 스코프와 동일하게 관리한다. == singleton */

    @Test
    @DisplayName("엔티티 매니저 팩토리 생성 확인하기")
    void testCreateFactory() {

        EntityManagerFactory factory
                = EntityManagerFactoryGenerator.getInstance();

        EntityManagerFactory factory2
                = EntityManagerFactoryGenerator.getInstance();

        System.out.println("factory.hashCode() = " + factory.hashCode());
        System.out.println("factory2.hashCode() = " + factory2.hashCode());

        // 공장이 잘 생성 되었는지 확인
        Assertions.assertNotNull(factory);
        // 팩토리 인스턴스가 singleton 인지 확인
        Assertions.assertEquals(factory, factory2);
    }

    /* comment.
    *   엔티티 매니저란?
    *   엔티티 매니저는 엔티티를 저장하는 메모리 상의 DB 를 관리하는 인스턴스이다.
    *   저장, 수정, 삭제, 조회 등의 엔티티 관련 모든 일을 factory 내부에서 진행하게된다.
    *   엔티티 매니저는 동시성에 안전하지 못하기 때문에 1개의 요청 시 매니저를 생성하게 된다. (request-scope)와 일치 */

    @Test
    @DisplayName("엔티티 매니저 생성 확인")
    void testCreateManager() {
        // 아까 만든 매니저 생성하는 메소드
        // 매니저를 새로 생성할때마다 다른 hashCode 반환
        EntityManager manager = EntityManagerGenerator.getInstance();

        EntityManager manager2 = EntityManagerGenerator.getInstance();

        System.out.println("manager.hashCode() = " + manager.hashCode());
        System.out.println("manager2.hashCode() = " + manager2.hashCode());

        // 매니저가 잘 생성되었는지 확인
        Assertions.assertNotNull(manager);

        // 서로 다른 인스턴스가 맞는 지 test
        Assertions.assertNotEquals(manager, manager2);
    }

}
