package com.example.jpa_study.entity.listener;

import com.example.jpa_study.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    // 주소 정보
    private String city; // 도시
    private String district; // 구

    @Column(name = "address_detail")
    private String detail; // 상세 주소
    private String zipCode; // 우편번호
}
