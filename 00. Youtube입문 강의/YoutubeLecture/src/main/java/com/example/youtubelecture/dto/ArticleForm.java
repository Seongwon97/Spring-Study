package com.example.youtubelecture.dto;

import com.example.youtubelecture.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {
    private String title;
    private String content;
    // 변수명은 articles의 html텍스트에서 name이름과 동일하게 해야한다.

    /* @AllArgsConstructor를 추가하면 주석처리된 constructor code를 추가하지 않아도 자동으로 추가된다. (lombok사용)
    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;

    }*/

    /* @ToString을 추가하면 toString을 override하지 않아도 된다. (lombok사용)
    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }*/


    public Article toEntity() {
        // id는 자동생성이라 null로 한 것이다.
        return new Article(null, title, content);
    }
}
