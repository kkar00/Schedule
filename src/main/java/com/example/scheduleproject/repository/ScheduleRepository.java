package com.example.scheduleproject.repository;

import com.example.scheduleproject.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    //<Schedule, Long> 의미: 이 Repository는 Schedule 엔티티를 다룬다. Schedule 엔티티의 기본 키(PK) 타입은 Long이다.
}
