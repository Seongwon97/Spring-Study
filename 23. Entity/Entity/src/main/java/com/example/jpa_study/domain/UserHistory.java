package com.example.jpa_study.domain;

// History data의 경우 DB에서 특정 값이 update되면 해당 값의 복사본을 다른 테이블에 저장하는 경우가 있다.

import com.example.jpa_study.domain.listener.Auditable;
import com.example.jpa_study.domain.listener.MyEntityListener;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
// extends BaseEntity를 상속하였는데 출력이 잘 되지 않아 아래의 2개의 Annotation을 추가
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
//@EntityListeners(value = MyEntityListener.class)
//@EntityListeners(value = AuditingEntityListener.class)
public class UserHistory extends BaseEntity implements Auditable {
    // implements Auditable을 구현해야지 MyEntityListener.class의 Listener를 사용가능 (그렇게 코딩을 했기에)
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private String name;

    private String email;

//    private LocalDateTime createdAt;
//
//    private LocalDateTime updatedAt;
// BaseEntity를 상속받아 createdAt, updatedAt가 Column으로 생긴다.
}
