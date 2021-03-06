package com.example.youtubelecture.controller;

import com.example.youtubelecture.dto.ArticleForm;
import com.example.youtubelecture.entity.Article;
import com.example.youtubelecture.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j // logging을 위한 annotation (해당 annotation을 사용하면 아래의 log.info를 사용 가능)
public class ArticleController {
    // @Autowired를 붙이면 Spring boot가 미리 생성해놓은 객체를
    // 자동으로 연결시켜줘서 객체를 new를 통해 새로 만들지 않아도 된다.
    @Autowired
    private ArticleRepository articleRepository;


    @GetMapping("/atricles/view")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        // data를 객체로 받아오려면 parameter로 dto를 넣어줘야한다.
        // dto에 있는 ArticleForm을 parameter로 설정

        // Logging사용~!
        // console에 print를 하는 것은 실제로 서버 성능에 영향을 끼쳐서 logging으로 대체해야한다.
        // System.out.println(form.toString()); -> logging으로 대체
        log.info(form.toString());

        // DTO데이터를 DB에 넣기!
        // 1. DTO를 Entity로 변환하기
        Article article = form.toEntity(); // Article class를 만들고, ArticlaForm class에 toEntity()메서드를 만들어줘야한다.
        //System.out.println(article.toString());
        log.info(article.toString());

        // 2. Repository에게 Entity를 DB안에 저장하게 하기!
        Article saved = articleRepository.save(article);
        //System.out.println(saved.toString());
        log.info(saved.toString());

        return "";
    }
}
