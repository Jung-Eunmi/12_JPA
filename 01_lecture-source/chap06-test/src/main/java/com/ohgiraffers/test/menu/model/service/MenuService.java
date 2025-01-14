package com.ohgiraffers.test.menu.model.service;

import com.ohgiraffers.test.menu.entity.Category;
import com.ohgiraffers.test.menu.entity.Menu;
import com.ohgiraffers.test.menu.model.dao.CategoryRepository;
import com.ohgiraffers.test.menu.model.dao.MenuRepository;
import com.ohgiraffers.test.menu.model.dto.CategoryDTO;
import com.ohgiraffers.test.menu.model.dto.MenuDTO;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.IllegalQueryOperationException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository repository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    public List<MenuDTO> findMenuListAndCategoryName() {

        List<Menu> menuList = repository.findAll(Sort.by("menuCode").ascending());

        return menuList.stream().map(menu -> modelMapper.map(menu, MenuDTO.class)).collect(Collectors.toList());
    }

    public List<CategoryDTO> findCategory() {

        List<Category> categoryList = categoryRepository.findAll();

        return categoryList.stream().map(category -> modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
    }

    public List<MenuDTO> findMenu(int categoryCode) {

        List<Menu> menuList = repository.findByCategory_CategoryCode(categoryCode);

        return menuList.stream().map(menu -> modelMapper.map(menu, MenuDTO.class)).collect(Collectors.toList());

    }
}
