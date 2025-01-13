package com.ohgiraffers.springdatajpa.menu.controller;

import com.ohgiraffers.springdatajpa.common.PageNation;
import com.ohgiraffers.springdatajpa.common.PagingButton;
import com.ohgiraffers.springdatajpa.menu.model.dto.MenuDTO;
import com.ohgiraffers.springdatajpa.menu.model.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/menu")
@RequiredArgsConstructor    // 필드에 final 키워드가 붙은 것은 자동으로 생성자 주입을 해준다.
                            // public menuController(MenuService menuService) {this.menuService = menuService} 생략 가능!
@Slf4j  // Lombok 라이브러리에서 제공하는 로깅 관련 어노테이션
public class menuController {

    private final MenuService menuService;

    @GetMapping("/{menuCode}")
    public String findMenuByPathVariable(@PathVariable int menuCode, Model model){

        MenuDTO resultMenu = menuService.findMenuByMenuCode(menuCode);

        model.addAttribute("result", resultMenu);
        return "menu/detail";
    }

    @GetMapping("/list")
    public String findAllMenu(Model model, @PageableDefault Pageable pageable) {

        /* comment. 페이징 처리 하지 않은 findAll() */
//        List<MenuDTO> menuList = menuService.findMenuList();
//
//        model.addAttribute("menus", menuList);

        // @Slf4j 을 클래스위에 명시해주면 로그를 확인 할 수 있다.
        log.info("pageable : {}" , pageable);

        Page<MenuDTO> menuList = menuService.findMenuListByPaging(pageable);

        log.info("조회할 내용 목록 : {}" , menuList.getContent());
        log.info("총페이지 수 : {}" , menuList.getTotalPages());
        log.info("총메뉴의 수 : {}" , menuList.getTotalElements());
        log.info("해당 페이지에 표현 될 요소의 수 : {}" , menuList.getSize());
        log.info("첫 페이지 여부 : {}", menuList.isFirst());
        log.info("마지막 페이지 여부 : {}", menuList.isLast());
        log.info("정렬방식 : {}", menuList.getSort());
        log.info("여러 페이지 중 현재 인덱스 : {}", menuList.getNumber());

        PagingButton pagingButton = PageNation.getPagingInfo(menuList);
        model.addAttribute("menus", menuList);
        model.addAttribute("paging", pagingButton);

        return "menu/list";
    }
}
