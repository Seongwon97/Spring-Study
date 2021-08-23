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


    // PathVariable
    // 변화하는 구간에 대해서는 PathVariable을 사용해야한다.
    // http://localhost:9090/api/get/path-variable/{name}
    // {name} 에 들어가는 값을 읽어올 때 사용

    // 변할 수 정보를 이용하는 예시 ex)사용자의 이름, 아이디 등등
    // name에 여러 값이 들어올 수 있는데 값 별로 설정을 해줄 수 없기에 사용한다.
    // 변경될 수 있는 부분을 {}로 지정해준다.
    // {name}에 들어가는 값은 @PathVariable을 통해 지정해줄 수 있다.

//    @GetMapping("path-variable/{name}")
//    public String pathVariable(@PathVariable String name){
//        // 일반적으로 34번,35번,38번 line의 변수명(name)이 같아야한다.
//        System.out.println("PathVariable : " + name);
//        return name;
//    }

    // 프로그래밍을 하며 변수가 꼬여 같은 변수에는 이름을 다르게 설정해야할 때는
    // @PathVariable에 (name=~~)를 붙여 해결할 수 있다.
   @GetMapping("path-variable/{name}")
    public String pathVariable(@PathVariable(name = "name") String pathName){
        // 일반적으로 34번,35번,38번 line의 변수명(name)이 같아야한다.
        System.out.println("PathVariable : " + pathName);
        return pathName;
    }




}
