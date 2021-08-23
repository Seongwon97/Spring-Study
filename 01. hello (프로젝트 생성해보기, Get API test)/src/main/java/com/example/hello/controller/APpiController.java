package com.example.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// @RestController annotation을 추가해주면 해당 class는 REST API를 처리하는 controller로 등록이 된다.
// @RequestMapping("/api")은 URI를 지정해주는 annotation이다.
@RestController
@RequestMapping("/api")
public class APpiController {

    // Get요청이 http://localhost:9090/api/hello으로 들어오면 반환할 결과를 설정
    @GetMapping("/hello") // http://localhost:9090/api/hello에 mapping을 시켰다.
    public String hello(){
        return "hello spring boot!";
        //http://localhost:9090/api/hello으로 Get요청이 들어오면 hello spring boot!을 return할 것이다.
    }
}
