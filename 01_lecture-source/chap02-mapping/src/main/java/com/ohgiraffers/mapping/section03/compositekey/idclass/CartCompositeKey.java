package com.ohgiraffers.mapping.section03.compositekey.idclass;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CartCompositeKey {

    private int cartOwner; // 카트 주인
    private int addedBook; // 추가 된 책
}
