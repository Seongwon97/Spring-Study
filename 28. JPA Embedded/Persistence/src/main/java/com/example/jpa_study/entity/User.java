// Entity의 값을 조회, 저장 등의 동작을 하며 사용하는 것은 repository에서 한다.

package com.example.jpa_study.entity;

import com.example.jpa_study.entity.listener.Address;
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

//    // 주소 정보
//    private String city; // 도시
//    private String district; // 구
//    private String detail; // 구체적인 주소
//    private String zipCode; // 우편번호

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "home_city")), // city를 home_city라는 column명으로 사용
            @AttributeOverride(name = "district", column = @Column(name = "home_district")),
            @AttributeOverride(name = "detail", column = @Column(name = "home_address_detail")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "home_zipCode"))
    })
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "company_city")), // city를 home_city라는 column명으로 사용
            @AttributeOverride(name = "district", column = @Column(name = "company_district")),
            @AttributeOverride(name = "detail", column = @Column(name = "company_address_detail")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "company_zipCode"))
    })
    private Address companyAddress;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @ToString.Exclude
    List<UserHistory> userHistories = new ArrayList<>();


    @OneToMany
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

}
