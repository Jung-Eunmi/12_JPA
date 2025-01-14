package com.ohgiraffers.test.menu.controller;

import com.ohgiraffers.test.menu.model.dto.CategoryDTO;
import com.ohgiraffers.test.menu.model.dto.MenuDTO;
import com.ohgiraffers.test.menu.model.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/list")
    public String findMenuListAndCategoryName(Model model) {

        List<MenuDTO> menuList = menuService.findMenuListAndCategoryName();

        model.addAttribute("menuList", menuList);

        return "/menu/list";
    }

    @GetMapping("/menuByCategory")
    public void findMenuByCategory() {}

    @GetMapping(value = "/findCategory")
    @ResponseBody
    public List<CategoryDTO> findCategory() {
        return menuService.findCategory();
    }

    @GetMapping("/findList")
    public String findMenu(@RequestParam int categoryCode, Model model) {

        List<MenuDTO> menuList = menuService.findMenu(categoryCode);

        model.addAttribute("menuList", menuList);



        return "/menu/findList";
    }
}
