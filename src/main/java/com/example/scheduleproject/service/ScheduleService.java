package com.example.scheduleproject.service;

import com.example.scheduleproject.dto.CreateRequestDto;
import com.example.scheduleproject.dto.CreateResponseDto;
import com.example.scheduleproject.dto.GetScheduleResponseDto;
import com.example.scheduleproject.dto.PatchScheduleRequestDto;
import com.example.scheduleproject.entity.Schedule;
import com.example.scheduleproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    //일정 생성 CREATE
    public CreateResponseDto createSchedule(CreateRequestDto createRequestDto) {
        // 1. DTO → Entity
        Schedule schedule = new Schedule();   //빈 Schedule 엔티티를 하나 생성함
        schedule.setName(createRequestDto.getName());   //요청에서 받은 name 값을 엔티티에 넣음
        schedule.setTitle(createRequestDto.getTitle());
        schedule.setPassword(createRequestDto.getPassword());

        // 2. DB 저장
        //생성한 Schedule 엔티티를 DB에 저장함
        //저장되면서 id, updatedAt 같은 필드도 자동으로 채워지고 이 객체가 saved에 담김
        Schedule saved = scheduleRepository.save(schedule);

        // 3. Entity → 응답 DTO로 변환
        return new CreateResponseDto (
                //저장된 Schedule 엔티티를 가지고 응답용 DTO를 생성함
                //클라이언트가 필요로 하는 필드만 골라서 포함
                saved.getId(),
                saved.getName(),
                saved.getTitle(),
                saved.getCreatedAt(),
                saved.getUpdatedAt()
        );
    }

    //전체 일정 조회 GET
    public List<GetScheduleResponseDto> getAllSchedules() {
        //반환 타입은 List<GetScheduleResponseDto> → 클라이언트에게 줄 조회 전용 DTO 리스트

        List<Schedule> scheduleList = scheduleRepository.findAll();
        //DB에 있는 Schedule 엔티티들을 전부 불러온다
        //findAll()은 JpaRepository가 제공하는 메서드이며, SELECT * FROM schedule과 같은 역할
        //결과는 List<Schedule> 형태로 저장

        return scheduleList.stream()  //scheduleList를 Java Stream API로 변환
                .map(schedule -> new GetScheduleResponseDto( //각 schedule 객체를 → GetScheduleResponseDto 객체로 변환한다는 뜻
                        schedule.getId(),   //Schedule 엔티티의 데이터를 꺼내서 DTO 생성자에 순서대로 전달하면 Dto 객체가 만들어짐
                        schedule.getName(),
                        schedule.getTitle(),
                        schedule.getCreatedAt(),
                        schedule.getUpdatedAt()
                ))
                .collect(Collectors.toList());
        //변환된 GetScheduleResponseDto 객체들을 리스트로 수집해서 반환
        //Stream<GetScheduleResponseDto> → List<GetScheduleResponseDto>로 변환
    }

    // 단건 일정 조회 (ID로 조회) GET
    public GetScheduleResponseDto getScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다. ID=" + id));

        return new GetScheduleResponseDto(
                schedule.getId(),
                schedule.getName(),
                schedule.getTitle(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }

    //수정 Patch
    public GetScheduleResponseDto updateSchedule(Long id, PatchScheduleRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));

        // 비밀번호 확인
        if (!schedule.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 제목 수정
        schedule.setTitle(requestDto.getTitle());

        // 저장 (변경감지)
        Schedule updated = scheduleRepository.save(schedule);

        return new GetScheduleResponseDto(
                updated.getId(),
                updated.getName(),
                updated.getTitle(),
                updated.getCreatedAt(),
                updated.getUpdatedAt()
        );
    }

    // 삭제 DELETE
    public void deleteSchedule(Long id, String password) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));

        if (!schedule.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        scheduleRepository.delete(schedule);
    }




}
