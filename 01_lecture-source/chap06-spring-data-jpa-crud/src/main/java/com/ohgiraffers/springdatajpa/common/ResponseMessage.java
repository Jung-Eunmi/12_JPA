package com.ohgiraffers.springdatajpa.common;

import lombok.*;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseMessage {

    private int httpStatusCode; // 상태코드
    private String message; // 상태코드에 대한 메세지
    private Map<String, Object> results; // 데이터

}
