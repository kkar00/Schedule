package com.example.scheduleproject.controller;

import com.example.scheduleproject.dto.CreateRequestDto;
import com.example.scheduleproject.dto.CreateResponseDto;
import com.example.scheduleproject.dto.GetScheduleResponseDto;
import com.example.scheduleproject.dto.PatchScheduleRequestDto;
import com.example.scheduleproject.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    //속성
    private final ScheduleService scheduleService;


    //생성자
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    //기능

    // 일정 생성 CREATE
    @PostMapping("/create")
    public ResponseEntity<CreateResponseDto> createAPI(@RequestBody CreateRequestDto createRequestDto) {
        CreateResponseDto responseDto = scheduleService.createSchedule(createRequestDto);
        return ResponseEntity.status(201).body(responseDto);
    }

    // 전체일정 조회 GET
    @GetMapping
    public ResponseEntity<List<GetScheduleResponseDto>> getAllSchedules() {
        List<GetScheduleResponseDto> responseList = scheduleService.getAllSchedules();
        return ResponseEntity.ok(responseList);
    }

    // 단건 일정 조회 GET
    @GetMapping("/{id}")
    public ResponseEntity<GetScheduleResponseDto> getScheduleById(@PathVariable Long id) {
        GetScheduleResponseDto responseDto = scheduleService.getScheduleById(id);
        return ResponseEntity.ok(responseDto);
    }

    //수정 PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<GetScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody PatchScheduleRequestDto requestDto) {

        GetScheduleResponseDto responseDto = scheduleService.updateSchedule(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    //삭제 DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long id,
            @RequestParam String password) {

        scheduleService.deleteSchedule(id, password);
        return ResponseEntity.ok().build();  // 본문 없이 200 OK 응답

    }




}
