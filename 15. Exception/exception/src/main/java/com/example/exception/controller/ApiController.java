package com.example.exception.controller;

import com.example.exception.dto.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class ApiController {

    @GetMapping("/get")
    public User get(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age){
        // @RequestParam(required = false)는 해당 parameter가 없어도 실행된다는 뜻. (default값은 true)
        User user = new User();
        user.setAge(age);
        user.setName(name);


        // age에 null값을 주고 연산을 하게 하여 강제로 exception발생시긴다.
        int a= 10+age;
        return user;
    }

    @PostMapping("/post")
    public User post(@Valid @RequestBody User user){
        System.out.println(user);
        return user;
    }
}
