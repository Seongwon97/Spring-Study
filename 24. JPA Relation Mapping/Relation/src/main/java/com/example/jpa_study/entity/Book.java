package com.example.jpa_study.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    private Long authorId;


//    private Long publisherId;

    // Book에서 BookReviewInfo가져오기
    @OneToOne(mappedBy = "book") // BookReviewInfo의 연관 Key인 book_review_info_id를 table의 속성으로 갖지 않는다.
    @ToString.Exclude // ToString에서 제외
    private BookReviewInfo bookReviewInfo;
    // Entity Relationship을 사용하는 경우 ToString과 같은 것은 순환참조가 걸리게 된다.
    // 그래서 특별히 필요한 경우가 아니면 Relation은 단방향으로 걸거나 양방향으로 걸면 ToString에서 제외하는 작업을 해줘야한다.
    // 그러지 않으면 StackOverFlow오류가 발생한다.

    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();


    @ManyToOne
    @ToString.Exclude
    private Publisher publisher;

//    @ManyToMany // One이 껴있는 경우는 JoinColumn을 선택하여 중간에 생성되는 Table을 삭제할 수 있지만
//    // ManyToMany의 경우는 Foreign key를 통해 특정 데이터를 분리할 수 없기에 중간 테이블을 통해서 데이터를 조회해야한다.
//    @ToString.Exclude
//    private List<Author> authors = new ArrayList<>();


//    public void addAuthor(Author... author){
//        Collections.addAll(this.authors, author);
//    }

    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<BookAndAuthor> bookAndAuthors = new ArrayList<>();

    public void addBookAndAuthors(BookAndAuthor... bookAndAuthors) {
        Collections.addAll(this.bookAndAuthors, bookAndAuthors);
    }

}
