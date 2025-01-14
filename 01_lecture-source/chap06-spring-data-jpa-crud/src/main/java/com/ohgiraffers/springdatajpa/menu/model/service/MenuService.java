package com.ohgiraffers.springdatajpa.menu.model.service;

import com.ohgiraffers.springdatajpa.menu.entity.Category;
import com.ohgiraffers.springdatajpa.menu.entity.Menu;
import com.ohgiraffers.springdatajpa.menu.model.dao.CategoryRepository;
import com.ohgiraffers.springdatajpa.menu.model.dao.MenuRepository;
import com.ohgiraffers.springdatajpa.menu.model.dto.CategoryDTO;
import com.ohgiraffers.springdatajpa.menu.model.dto.MenuDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository repository;
    // Bean 으로 등록한 ModelMapper 의존성 주입
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    /* 메뉴 코드로 특정 메뉴 조회하기 */
    public MenuDTO findMenuByMenuCode(int menuCode) {
                                                    // findById 를 사용할때 DB 에 없는 값이 넘어올때에 대한 예외처리(필수)
        Menu foundMenu = repository.findById(menuCode).orElseThrow(IllegalArgumentException::new);

        // return 하려면 Entity -> MenuDTO 로 변환 해주어야하므로, 매번 귀찮은 방식대신
        // https://mvnrepository.com/artifact/org.modelmapper/modelmapper
        // implementation 'org.modelmapper:modelmapper:3.1.1' 를 build.gradle 에 의존성 주입해주고
        // Config 에 Bean 으로 Entity 와 DTO 를 매칭과 private 필드에 접근하기 위한 설정을 해주면된다.

        // Entity -> DTO
        // .map(변환 대상, 변환 할 타입)
        return modelMapper.map(foundMenu, MenuDTO.class);
    }

    /* 페이징 처리하지 않은 전체 메뉴 리스트 조회하기 */
    public List<MenuDTO> findMenuList() {

//        List<Menu> menuList = repository.findAll(); 정렬 없는 findALL
                                                // menuPrice 를 desc 로 Sort 필드명으로 지정!
        List<Menu> menuList = repository.findAll(Sort.by("menuPrice").descending());

        // stream : 컬렉션(List 등등) 데이터를 처리하기 위해 나열해주는 메소드
        return menuList.stream()
                // map : 스트림화 된 데이터들을 하나하나씩 꺼내 매핑 및 변환
                .map(menu -> modelMapper.map(menu, MenuDTO.class))
                // collect : 스트림화 된 데이터를 다시 List 로 변환
                .collect(Collectors.toList());
    }

    /* 페이징 처리 한 전체 메뉴 리스트 조회하기 */
    public Page<MenuDTO> findMenuListByPaging(Pageable pageable) {

        pageable = PageRequest.of(
                pageable.getPageNumber() <= 0? 0 : pageable.getPageNumber() -1,
                pageable.getPageSize(),
                Sort.by("menuCode").descending()
        );

        Page<Menu> menuList = repository.findAll(pageable);

        return menuList.map(menu -> modelMapper.map(menu, MenuDTO.class));
    }

    /* 쿼리 메소드로 입력 한 메뉴 가격 이상인 메뉴들 조회하기 */
    public List<MenuDTO> findByMenuPrice(int menuPrice) {
         // 정렬 X          // And 를 붙여 조건을 더 추가할 수 있다. 하지만 메소드명이 길기 때문에 가독성이 좋지 않다.
//        List<Menu> menuList = repository.findByMenuPriceGreaterThan(menuPrice);

        // 메뉴가격으로 오름차순 정렬
        List<Menu> menuList = repository.findByMenuPriceGreaterThanOrderByMenuPrice(menuPrice);

        return menuList.stream().map(menu -> modelMapper.map(menu, MenuDTO.class)).collect(Collectors.toList());
    }

    public List<CategoryDTO> findAllCategory() {

        List<Category> categoryList = categoryRepository.findAllCategory();

        return categoryList.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public void registNewMenu(MenuDTO newMenu) {

        // 지금까지는 Entity 를 DTO 로 변환했다면
        // DML 구문에서는 DTO 타입을 Entity 로 변환을 해야
        // Persistence Context == JPA 가 관리를 해준다.
        repository.save(modelMapper.map(newMenu, Menu.class));
    }

    @Transactional
    public void modifyMenu(MenuDTO modifyMenu) {
        /* comment. update 는 엔티티를 특정해서 필드 값을 변경해주면 된다.
        *   JPA 는 변경 감지 기능이 있어서 하나의 엔티티를 특정하여
        *   필드 값을 변경하면 변경 된 값으로 flush(반영) 해준다. */

        // 특정 엔티티 찾기
        Menu foundMenu = repository.findById(modifyMenu.getMenuCode()).orElseThrow(IllegalArgumentException::new);
        System.out.println("찾은 Entity 값 = " + foundMenu);

        /* comment. 값을 수정하는 방식1 - setter 를 통해 update 가능 (지양하는 방법) */
//        foundMenu.setMenuName(modifyMenu.getMenuName());
//        System.out.println("setter 사용 후 foundMenu = " + foundMenu);

        /* comment. 값을 수정하는 방식2 - 엔티티에 @Builder 어노테이션을 통해 update 기능 */
//        foundMenu = foundMenu.toBuilder()
//                             .menuName(modifyMenu.getMenuName())
//                             .build();
//
//        // build 를 통해서 foundMenu 를 새롭게 탄생시킨 후 save 메소드를 통해 JPA 에게 전달
//        repository.save(foundMenu);

        /* comment. 값을 수정하는 방식3 - Entity 내부에 builder 패턴을 구현 */
//        foundMenu = foundMenu.menuName(modifyMenu.getMenuName()).builder();
//
//        repository.save(foundMenu);
    }

//    @Transactional
//    public void deleteByMenuCode(int menuCode) {
//
//        modelMapper.map(menuCode, MenuDTO.class);
//
//        repository.deleteById(menuCode);
//
//
//    }

    @Transactional
    public void deleteMenu(int menuCode) {
        repository.deleteById(menuCode);
    }
}
