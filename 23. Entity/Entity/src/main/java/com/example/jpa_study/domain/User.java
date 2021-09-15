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

    //프로그래밍을 하면서 사용하는 변수명과 DB에 저장되는 column명을 다르게 하려면
    //@Column annotation에서 name을 설정해준다.
    //@Column(name = "crtdat")

    //@Column(nullable = false) // nullable을 false로 해주면 filed가 notNull column으로 설정된다.

    //@Column(unique = true) // 해당 column을 unique로 설정 (12번 line에서 table을 사용하여 지정한 unique는 복합 column을 지정할 때 사용)
    // 12번 라인의 unique는 name과 email을 모두 unique하게 하는 복합 column의 예이다.

    //@Column(updatable = false) // updatable=false를 하면 해당 column은 update가 불가능하다

    //@Column(insertable = false) // insertable=false를 하면 해당 column은 insert가 불가능하다

    private LocalDateTime createdAt; // 생성된 시간


    private LocalDateTime updatedAt; // 업데이트된 시간



    // DB에서는 enum의 값들을 기본적으로 데이터를 0,1,~순으로 값을 저장한다.
    // 아래 Gender를 예시로 들면 DB에 값을 저장하면 MALE은 0, FEMALE은 1로 DB에 저장된다.
    // User객체의 gender를 변수에 @Enumerated를 확인해보면 default값이 ORDINAL인 것을 확인할 수 있다.(STRING이 아니다.)
    // String으로 바꾸려면 @Enumerated(value = EnumType.STRING)으로 변경해줘야한다.
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Transient // @Transient를 붙이면 해당 변수는 DB와 상관없이 객체의 데이터로 쓸 수 있게된다.
    private String testData;



}