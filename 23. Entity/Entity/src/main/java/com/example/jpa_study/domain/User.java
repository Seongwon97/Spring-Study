// Entity의 값을 조회, 저장 등의 동작을 하며 사용하는 것은 repository에서 한다.

package com.example.jpa_study.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity // entity는 primary key가 꼭 필요하다. (@Id로 지정)
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class User {
    @Id // PK지정
    @GeneratedValue // entity를 만들때 자동으로 순차적으로 생성해줌
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String email;

    private LocalDateTime createdAt; // 생성된 시간
    private LocalDateTime updatedAt; // 업데이트된 시간


}
