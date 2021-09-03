package com.example.youtubelecture.controller;

import com.example.youtubelecture.dto.ArticleForm;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    @GetMapping("/atricles/view")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        // data를 객체로 받아오려면 parameter로 dto를 넣어줘야한다.
        // dto에 있는 ArticleForm을 parameter로 설정
        System.out.println(form.toString());
        return "";
    }
}
