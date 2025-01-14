package com.ohgiraffers.test.menu.model.dao;

import com.ohgiraffers.test.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    List<Menu> findByCategory_CategoryCode(int categoryCode);
}
