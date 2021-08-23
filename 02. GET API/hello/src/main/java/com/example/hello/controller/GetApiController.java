package com.example.hello.controller;

// 복습을 위해 따로 빼두었다.
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {



    // 기본적으로 괄호안에 ("/hello")라고 입력을 해도 value값으로 인식을 해서
    // path = "/hello"와 같은 결과를 내게 된다.
    // http://localhost:9090/api/get/hello
    @GetMapping(path = "/hello")
    public String Hello(){
        return "get Hello";
    }



    // 과거에 사용하던 방식으로 RequestMapping을 하면 Get, Post, Put, Delete가 모두 동작한다.
    // 위와 같이 Get만 사용하도록 지정하고 싶다면 method parameter를 추가해주면 된다.
    @RequestMapping(path = "/hi", method = RequestMethod.GET)
    public String hi(){
        return "get hi";
    }


 
}
