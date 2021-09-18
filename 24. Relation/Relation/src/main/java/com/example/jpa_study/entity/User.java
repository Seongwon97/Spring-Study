// Entity의 값을 조회, 저장 등의 동작을 하며 사용하는 것은 repository에서 한다.

package com.example.jpa_study.entity;

import com.example.jpa_study.entity.listener.UserEntityListener;
import lombok.*;

import javax.persistence.*;

@Entity // entity는 primary key가 꼭 필요하다. (@Id로 지정)
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@EntityListeners(value = {UserEntityListener.class})
public class User extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String email;


    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Transient
    private String testData;



}
