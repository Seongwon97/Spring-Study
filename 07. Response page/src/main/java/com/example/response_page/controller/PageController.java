package com.example.response_page.controller;

import com.example.response_page.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//HTML page를 return하는 방법
@Controller
public class PageController {


    @RequestMapping("/main")
    public String main(){
        return "main.html";
    }
    // 이러한 controller는 return 자체가 string이 되면 자동으로 resource에 있는 html파일을 찾아가게 된다.
    // 이러한 방법으로 json은 어떻게 내려줄까? ResponseEntity를 사용하거나 ResponseBody를 사용한다.


    // Controller라는 annotation은 return값이 String이면 html파일을 찾게 되는데
    // ResponseBody를 붙여주면 객체가 return했을 때 resource(html파일)을 찾지 않고 ResponseBody를 만들어 보낸다.
    // (json 타입으로 return)
    @ResponseBody
    @GetMapping("/user")
    public User user(){
        var user = new User();
        // var는 타입추론을 해서 맞춰준다.
        user.setUser("오성원");
        user.setAddress("용인시");
        return user;
    }
}
