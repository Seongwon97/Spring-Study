package com.example.jpa_study.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
@EntityListeners(value = MyEntityListener.class) // MyEntityListener.class를 Entity Listener로 설정
// 여러 Class에서 같은 @PrePersist와 같은 listener를 사용할 때 사용한다.
// 공통적인 부분을 구현해두고 Listener를 annotation코드 한줄로 여러 객체에 주입 가능하다!! (반복적인 코딩을 줄일 수 있다.)
public class Book implements Auditable{
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String author;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

//    @PrePersist
//    public void prePersist() {
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    public void preUpdate() {
//        this.updatedAt = LocalDateTime.now();
//    }
}
