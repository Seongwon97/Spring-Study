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


    //////////////////////////
    ///  Entity Listener   ///
    //////////////////////////
    // Entity가 동작하는 몇가지 방법에 대해 Listening하고 있다.
    // JPA에서 제공하는 Event는 7가지가 있다.
//    @PrePersist : Persist(indert)메서드가 호출되기 전에 실행되는 메서드
//    @PreUpdate : merge메서드가 호출되기 전에 실행되는 메서드
//    @PreRemove : Delete메서드가 호출되기 전에 실행되는 메서드
//    @PostPersist : Persist(indert)메서드가 호출된 이후에 실행되는 메서드
//    @PostUpdate : merge메서드가 호출된 후에 실행되는 메서드
//    @PostRemove : Delete메서드가 호출된 후에 실행되는 메서드
//    @PostLoad : Select조회가 일어난 직후에 실행되는 메서드



    // UserRepositoryTest에서 listenerTest()를 테스트 하기 위한 method들!!
    // log를 찍어서 test하기 위해 method생성
//    @PrePersist
//    public void prePersist() {
//        System.out.println(">>> prePersist");
//    }
//
//    @PostPersist
//    public void postPersist() {
//        System.out.println(">>> postPersist");
//    }
//
//    @PreUpdate
//    public void preUpdate() {
//        System.out.println(">>> preUpdate");
//    }
//
//    @PostUpdate
//    public void postUpdate() {
//        System.out.println(">>> postUpdate");
//    }
//
//    @PreRemove
//    public void preRemove() {
//        System.out.println(">>> preRemove");
//    }
//
//    @PostRemove
//    public void postRemove() {
//        System.out.println(">>> postRemove");
//    }
//
//    @PostLoad
//    public void postLoad() {
//        System.out.println(">>> postLoad");
//    }


    // UserRepositoryTest에서 prePersistTest()를 테스트 하기 위한 method들!!
    // 실제로 prePersist가 사용되는 예시 설명 + Test
    // DB를 설계하다보면 보통 Created time과 Updated time을 column을 만들어 저장한다.
    // 하지만 데이터를 생성할 때마다 user.setCreatedAt(LocalDateTime.now());와 같이 직접 넣어주다보면 잊어버리는 일도 발생할 것이다.
    // 그래서 prePersist를 사용하여 자동으로 Created time과 Updated time값을 넣어준다.
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }


}
