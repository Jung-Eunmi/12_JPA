package com.ohgiraffers.section01.entitymanager;

import jakarta.persistence.EntityManagerFactory;
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

        EntityManagerFactory factory =

    }
}
