package com.example.youtubelecture.entity;

// dto를 DB로 저장하기 위해 entity로 변환하는 단계가 필요
// Article class는 entity를 나타내느 class

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// Entity annotation을 붙이면 DB가 해당 객체를 인식 가능하다.
@Entity
public class Article {

    @Id // Entity class에는 entity들의 key값을 의미하는 Id변수가 필요하다.
    @GeneratedValue // Id를 자동생성해준다.
    private Long id;



    // 변수들에는 @Column annotation을 붙여서 DB에서 관리하는 Table에 연결되게 해준다.
    // @Column이 붙은 변수는 DB에서 field가 된다.
    @Column
    private String title;

    @Column
    private String content;

    public Article(Long id, String title, String content){
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
