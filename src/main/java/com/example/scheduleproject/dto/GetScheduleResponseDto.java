package com.example.scheduleproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class GetScheduleResponseDto {
    private Long id;
    private String name;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

