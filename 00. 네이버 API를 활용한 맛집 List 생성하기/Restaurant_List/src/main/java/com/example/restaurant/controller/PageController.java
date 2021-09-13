package com.example.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages")
public class PageController {

    // resources/ templates하위에 위치한 파일로 연결된다.
    @GetMapping("/main")
    public ModelAndView main(){
        return new ModelAndView("aaaa/main");
    }
}
