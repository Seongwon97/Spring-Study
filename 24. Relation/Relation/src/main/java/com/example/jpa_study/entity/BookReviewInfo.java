package com.example.jpa_study.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true) // 상속받는 class의 정보를 ToString에 포함시켜준다.(callSuper는 Default값이 false라 true로 바꿔줘야한다.)
@EqualsAndHashCode(callSuper = true)
public class BookReviewInfo extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookId;

    // Data Type을 지정할 때 Float, Integer가 아닌 primitive type인 float,int은 초기값이 0으로 초기화된다.
    // 또한 Table이 만들어 질 때 해당 Field가 not null로 만들어진다!
    // Float, Integer으로 하면 초기값이 Null이라 null체크를 안해주면 오류가 발생할 수 있다. (주의할 것!!)
    private float averageReviewScore;

    private int reviewCount;
}
