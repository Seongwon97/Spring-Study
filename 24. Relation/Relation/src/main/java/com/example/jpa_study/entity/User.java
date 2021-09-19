// Entity의 값을 조회, 저장 등의 동작을 하며 사용하는 것은 repository에서 한다.

package com.example.jpa_study.entity;

import com.example.jpa_study.entity.listener.UserEntityListener;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // entity는 primary key가 꼭 필요하다. (@Id로 지정)
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@EntityListeners(value = {UserEntityListener.class})
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String email;


    @Enumerated(value = EnumType.STRING)
    private Gender gender;

//    @Transient
//    private String testData;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    // Entity가 어떤 column과 join을 할 지 지정해주는 annotation (UserHistory Entity의 user_id 컬럼과 join한다.)
    // insertable = false, updatable = false를 넣은 이유는 userHistory의 경우는 User에서 임의로 변경해서는 안되는 ReadOnly를 설정하기 위해 추가
    List<UserHistory> userHistories = new ArrayList<>();
    // 변수명으로 복수를 쓰는게 최근 트렌드이다.
    // JPA에서는 해당 값을 사용할 때 값이 존재하지 않으면 자동으로 Bean List를 넣어주지만
    // Logic에 따라 Persist하기 전에 NullpointException이 발생할 수 있어서 = new ArrayList<>();로 초기화



}
