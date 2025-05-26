package com.example.scheduleproject.dto;

import lombok.Getter;

//@AllArgsConstructor 안 쓰는 이유
//@PostMapping에서 @RequestBody를 쓰면, 스프링이 JSON 데이터를 자바 객체로 자동 변환해주기 때문에 기본 생성자와 Getter만 있으면 됨
@Getter
public class CreateRequestDto {
    //클라이언트가 보낸 일정 등록 요청(Create) JSON을 자바 객체로 받기 위한 DTO

    private String name;
    private String title;
    private String password;

}
