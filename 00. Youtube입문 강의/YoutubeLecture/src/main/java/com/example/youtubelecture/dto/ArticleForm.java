package com.example.youtubelecture.dto;

import com.example.youtubelecture.entity.Article;

public class ArticleForm {
    private String title;
    private String content;
    // 변수명은 articles의 html텍스트에서 name이름과 동일하게 해야한다.

    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;

    }

    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Article toEntity() {
        // id는 자동생성이라 null로 한 것이다.
        return new Article(null, title, content);
    }
}
