package com.example.aop.controller;

import com.example.aop.dto.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @GetMapping("/get/{id}")
    public String  get(@PathVariable Long id, @RequestParam String name){
//        System.out.println("Get method");
//        System.out.println("Get method : "+id);
//        System.out.println("Get method : "+name);
        return id + " "+ name;
    }

    @PostMapping("/post")
    public User post(@RequestBody User user){
//        System.out.println("Post method : "+user);
        return user;
    }
}
