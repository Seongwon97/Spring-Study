package com.example.jpa_study.entity;

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
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    private Long authorId;

    private Long publisherId;

    // Book에서 BookReviewInfo가져오기
    @OneToOne
    private BookReviewInfo bookReviewInfo;
    // Relation의 경우는 단방향으로 걸거나 양방향으로 걸면 ToString에서 제외하는 작업을 해줘야한다.
    // 그러지 않으면 StackOverFlow오류가 발생한다.
}
