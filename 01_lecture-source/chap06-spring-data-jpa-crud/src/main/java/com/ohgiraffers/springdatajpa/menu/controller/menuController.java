package com.ohgiraffers.springdatajpa.menu.controller;

import com.ohgiraffers.springdatajpa.menu.model.dto.MenuDTO;
import com.ohgiraffers.springdatajpa.menu.model.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
@RequiredArgsConstructor    // 필드에 final 키워드가 붙은 것은 자동으로 생성자 주입을 해준다.
                            // public menuController(MenuService menuService) {this.menuService = menuService} 생략 가능!
public class menuController {

    private final MenuService menuService;

    @GetMapping("/{menuCode}")
    public String findMenuByPathVariable(@PathVariable int menuCode, Model model){

        MenuDTO resultMenu = menuService.findMenuByMenuCode(menuCode);

        model.addAttribute("result", resultMenu);
        return "menu/detail";
    }
}
