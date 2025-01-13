package com.ohgiraffers.springdatajpa.menu.model.service;

import com.ohgiraffers.springdatajpa.menu.entity.Menu;
import com.ohgiraffers.springdatajpa.menu.model.dao.MenuRepository;
import com.ohgiraffers.springdatajpa.menu.model.dto.MenuDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository repository;
    // Bean 으로 등록한 ModelMapper 의존성 주입
    private final ModelMapper modelMapper;

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
}
