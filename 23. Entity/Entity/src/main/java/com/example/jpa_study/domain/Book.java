package com.example.jpa_study.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
@EntityListeners(value = AuditingEntityListener.class) // MyEntityListener.class와 같은 기능을 하는데 JPA에서 제공하는 기본 기능을 사용하는 방법
// @EntityListeners(value = MyEntityListener.class) // MyEntityListener.class를 Entity Listener로 설정
// 여러 Class에서 같은 @PrePersist와 같은 listener를 사용할 때 사용한다.
// 공통적인 부분을 구현해두고 Listener를 annotation코드 한줄로 여러 객체에 주입 가능하다!! (반복적인 코딩을 줄일 수 있다.)
public class Book implements Auditable{
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String author;

    @CreatedDate // Auditing에서 데이터가 생성될 때 업데이트 해주길 바라는 변수에 @CreatedDate를 붙인다.
    private LocalDateTime createdAt;

    @LastModifiedDate // Auditing에서 데이터가 업데이트 될때 업데이트 해주길 바라는 변수에 LastModifiedDate를 붙인다.
    private LocalDateTime updatedAt;
    // @CreatedBy, @LastModifiedBy도 연습해볼것


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
