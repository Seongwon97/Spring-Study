package com.example.jpa_study.domain;

// History data의 경우 DB에서 특정 값이 update되면 해당 값의 복사본을 다른 테이블에 저장하는 경우가 있다.

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
@EntityListeners(value = MyEntityListener.class)
public class UserHistory implements Auditable{
    // implements Auditable을 구현해야지 MyEntityListener.class의 Listener를 사용가능 (그렇게 코딩을 했기에)
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private String name;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
