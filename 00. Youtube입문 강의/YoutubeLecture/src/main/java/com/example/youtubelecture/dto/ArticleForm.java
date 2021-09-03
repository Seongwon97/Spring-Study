package com.example.youtubelecture.dto;

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
}
