package com.example.scheduleproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
//Auditing 기능을 작동시키려면, 어떤 이벤트가 일어날 때 자동으로 반응하게 만들어야 한다
//Auditing: 등록하거나 수정하면 알려줘 → 자동으로 updatedAt에 시간 기록할게. 이걸 실행해주는 게 AuditingEntityListener
public abstract class BaseEntity {
    //직접 인스턴스를 생성할 일이 없으므로 abstract로 선언, 오직 상속용으로만 쓴다

    @CreatedDate    //처음 저장될 때만 값이 들어감
    @Column(name = "created_at", updatable = false)  //updatable = false 설정으로 생성 시간이 수정되지 못하게
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
