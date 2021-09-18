package com.example.jpa_study.domain.listener;

import java.time.LocalDateTime;

// EventLister를 구현하기 위해 반복되어 사용될 method들을 묶어 interface로 생성한다.
public interface Auditable {
    LocalDateTime getCreatedAt(); //Book, User의 변수들을 Lombok을 사용해 getter,setter를 만들어서 오류가 안뜸
    LocalDateTime getUpdatedAt();

    void setCreatedAt(LocalDateTime createdAt);
    void setUpdatedAt(LocalDateTime updatedAt);
}
