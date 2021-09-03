package com.example.youtubelecture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String  niceToMeetYou(Model model){
        model.addAttribute("username", "성원");
        // model이라는 객체를 사용하여 greetings.mustache에 있는 username에 성원이라는 값을 전달해준다.

        return "greetings"; // templetes/greetings.mustache 파일을 알아서 브라우저로 전송해준다.
        // hi라는 URL로 이동하면 greetings를 반환해준다.
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model){
        model.addAttribute("username", "Seongwon");
        return "goodbye";
    }
}
