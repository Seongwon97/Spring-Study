package com.example.jpa_study.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate // 필요한 정보만 update하게 해준다.
@Where(clause = "deleted = false") // 조회될 데이터의 제한을 거는 방법 -> Deleted가 false인 값들만 조회시키게 하겠다.
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    private Long authorId;



    @OneToOne(mappedBy = "book")
    @ToString.Exclude
    private BookReviewInfo bookReviewInfo;


    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

    // Book Entity가 persist가 될 때 Publisher도 persist를 실행시켜라~
    // Publisher가 merge가 될 때도 영속성 전이를 일으킨다~
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @ToString.Exclude
    private Publisher publisher;


    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<BookAndAuthor> bookAndAuthors = new ArrayList<>();

    public void addBookAndAuthors(BookAndAuthor... bookAndAuthors) {
        Collections.addAll(this.bookAndAuthors, bookAndAuthors);
    }

    // Delete는 실제 서비스에서는 잘 사용하지 않는다.
    // 보통은 아래와 같은 boolean값을 사용하여 삭제되었다~라는 표시를 하며 사용한다.
    // jpa 쿼리에서 해당 deleted값을 false인 것만 조회하게 하는 쿼리문들을 만들기에는 번거롭고 개발자가 깜빡 잊었을 때
    // 큰 문제가 발생할 수 있어서 21번째 줄과 같이 해당 변수같은 경우는 where 어노테이션을 사용하여 제한을 걸어준다.
    private boolean deleted;

}
