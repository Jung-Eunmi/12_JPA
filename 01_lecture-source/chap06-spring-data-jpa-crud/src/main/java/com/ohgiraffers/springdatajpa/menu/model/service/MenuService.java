package com.ohgiraffers.springdatajpa.menu.model.service;

import com.ohgiraffers.springdatajpa.menu.entity.Menu;
import com.ohgiraffers.springdatajpa.menu.model.dao.MenuRepository;
import com.ohgiraffers.springdatajpa.menu.model.dto.MenuDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

        // Menu 엔티티를 return 하려면 Entity -> MenuDTO 로 변환 해주어야하므로, 매번 귀찮은 방식대신
        // https://mvnrepository.com/artifact/org.modelmapper/modelmapper
        // implementation 'org.modelmapper:modelmapper:3.1.1' 를 build.gradle 에 의존성 주입해주고
        // Config 에 Bean 으로 Entity 와 DTO 를 매칭과 private 필드에 접근하기 위한 설정을 해주면된다.

        // Entity -> DTO
        // .map(변환 대상, 변환 할 타입)
        return modelMapper.map(foundMenu, MenuDTO.class);
    }
}
