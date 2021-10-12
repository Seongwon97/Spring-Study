package com.example.jpa_study.entity;

// History data의 경우 DB에서 특정 값이 update되면 해당 값의 복사본을 다른 테이블에 저장하는 경우가 있다.

import com.example.jpa_study.entity.listener.Address;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

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

    @ManyToOne
    @ToString.Exclude
    private User user;



}
