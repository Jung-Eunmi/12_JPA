package com.ohgiraffers.springdatajpa.menu.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_menu")
@NoArgsConstructor  // Entity 객체는 Setter 는 주입하지않는다.(데이터의 무결성을 유지하기 위해서)
@AllArgsConstructor
@Getter
@ToString
//@Builder(toBuilder = true)  // 2. @Builder 어노테이션 사용 update
public class Menu {

    @Id
    @Column(name = "menu_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menuCode;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private int menuPrice;

    @ManyToOne
    @JoinColumn(name = "category_code", referencedColumnName = "category_code")
    private Category category;

    @Column(name = "orderable_status")
    private String orderableStatus;

//    1. setter 사용 update (지양하는 방식)
//    public void setMenuName(String menuName) {
//        // setter 함수로 전달 받은 menuName 필드에 대입
//        this.menuName = menuName;
//    }

   // 3. builder 패턴 직접 구현
    public Menu menuName(String var) {
        this.menuName = var;
        return this;
    }
//
//    public Menu builder() {
//        return new Menu(menuCode, menuName,menuPrice,categoryCode,orderableStatus);
//    }
}
