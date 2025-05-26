package com.example.scheduleproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor   //응답 DTO는 우리가 직접 생성해야 하니까 모든 필드를 받는 생성자가 필요하다. @AllArgsConstructor 필요
public class CreateResponseDto {
    //일정을 DB에 저장한 후, 클라이언트에게 응답할 데이터만 골라서 보내는 DTO

    private Long id;
    private String name;
    private String title;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

}
