package com.example.jpa_study.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Publisher extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany
    @JoinColumn(name = "publisher_id")
    @ToString.Exclude
    private List<Book> book = new ArrayList<>();


    // booke은 list로 관리되어서 setter를 사용하려면 List로 한번에 넣어야하기에
    // 하나씩 넣으면서 가독성 높은 코드를 만들기 위해 아래와 같은 메서드 생성성
    public void addBook(Book book) {
        this.book.add(book);
    }
}
