package com.ohgiraffers.springdatajpa.menu.model.dao;

import com.ohgiraffers.springdatajpa.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/* comment.
*   JpaRepository 란?
*   EntityManager 와 EntityManagerFactory, EntityTransaction 을 구현한 클래스이다.
*   따라서 이제 우리는 미리 구현 된 클래스를 상속받아 더 이상 매니저를 명시적으로 호출 할 필요가 없다. */
@Repository
                            // JpaRepository 상속 받아줘야함 <Entity, Entity 식별자 타입>
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    List<Menu> findByMenuPriceGreaterThanOrderByMenuPrice(int menuPrice);

    @Query("SELECT m FROM Menu m WHERE m.category.categoryCode = :categoryCode")
    List<Menu> findByCategoryCode(@Param("categoryCode") int categoryCode);
}
