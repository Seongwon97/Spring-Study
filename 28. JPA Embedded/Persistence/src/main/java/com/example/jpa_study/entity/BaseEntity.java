package com.example.jpa_study.entity;

import com.example.jpa_study.entity.listener.Auditable;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data //CreatedAt,UpdatedAt은 Getter,Setter가 필요하여 추가
@MappedSuperclass // 해당 class의 Field를 상속받는 entity의 column으로 포함시켜 주는 Annotation
@EntityListeners(value = AuditingEntityListener.class)
public class BaseEntity  implements Auditable {
    @CreatedDate
    @Column(columnDefinition = "datetime(6) default now(6)", nullable = false, updatable = false)
    private LocalDateTime  createdAt;

    // columnDefinition은 @Column의 속성으로 data.sql에서 data를 insert할 때
    // created_at, updated_at이 자동으로 생성되지 않는데 이러한 문제를 해결해준다.

    // 현업에서는 auto-databade ddl을 사용하여 프로그램을 작성하는 경우가 드물어
    // columnDefinition을 사용하는 경우가 별로 없다.

    // columnDefinition의 경우는 해당 값을 해당 Column의 datatype을 포함한 모든 내용을 적어줘야 사용가능하다.
    // 코드 내부를 보면 columnDefinition이 존재하면 기존 값들을 null로 변경하고 해당 값을 넣게 된다
    // 위에서 말한 모든 내용이라는 것은 아래의 table에서 보이는 column명 뒤의 타입들을 의미한다.
    // (not null의 경우는 nullable = false)로 정해져서 적지 않아도 된다.
    /*
        create table author (
       id bigint not null auto_increment,
        created_at datetime(6) default now(6) not null,
        updated_at datetime(6) default now(6) not null,
        country varchar(255),
        name varchar(255),
        primary key (id)
    ) engine=InnoDB
     */
    @LastModifiedDate
    @Column(columnDefinition = "datetime(6) default now(6)", nullable = false)
    private LocalDateTime updatedAt;
}
