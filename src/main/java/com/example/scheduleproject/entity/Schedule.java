package com.example.scheduleproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity {

    //속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String title;
    private String password;

    //아래 부분은 BaseEntity에서 관리해주고 있음.
//    @Column(name = "updated_at") // DB 컬럼명
//    private LocalDateTime updatedAt;


}
