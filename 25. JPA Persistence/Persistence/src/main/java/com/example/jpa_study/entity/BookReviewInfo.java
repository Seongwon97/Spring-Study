package com.example.jpa_study.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true) // 상속받는 class의 정보를 ToString에 포함시켜준다.(callSuper는 Default값이 false라 true로 바꿔줘야한다.)
@EqualsAndHashCode(callSuper = true)
public class BookReviewInfo extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //private Long bookId;

    @OneToOne(optional = false) // Book과 1:1로 연관관계를 갖겠다~
    // @OneToOne의 optional의 Default값은 true라서 해당 값이 null일 수도 있으며 join을 할때도 left outer join을 하게된다.
    // optional을 true로 변경하면 해당 column은 무조건 존재하는 not null로 지정되며 join도 inner join으로 실행된다.
    // mappedBy을 사용하면 연관 key를 해당 Table에서는 갖지 않게된다.
    // -> mappedBy = "bookReviewInfo" 속성을 넣으면 Book의 연관 key인 book_id를 BookReviewInfo에서 갖지 않게 된다.
    private Book book;

    // Data Type을 지정할 때 Float, Integer가 아닌 primitive type인 float,int은 초기값이 0으로 초기화된다.
    // 또한 Table이 만들어 질 때 해당 Field가 not null로 만들어진다!
    // Float, Integer으로 하면 초기값이 Null이라 null체크를 안해주면 오류가 발생할 수 있다. (주의할 것!!)
    private float averageReviewScore;

    private int reviewCount;
}
