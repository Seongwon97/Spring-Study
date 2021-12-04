package inflearn.inflearnspringstart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!!");
        return "hello";
        // viewResolver가 resources/templates/hello.html을 찾아서 model을 넘기면서 이동시켜라~
    }
}
