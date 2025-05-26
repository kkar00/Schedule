package com.example.scheduleproject.dto;

import lombok.Getter;


@Getter
public class CreateRequestDto {
    //클라이언트가 보낸 일정 등록 요청(Create) JSON을 자바 객체로 받기 위한 DTO

    private String name;
    private String title;
    private String password;
}
