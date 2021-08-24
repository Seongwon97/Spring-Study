package com.example.response.controller;

import com.example.response.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    // Text
    @GetMapping("/text")
    public String text(@RequestParam String account){
        return account;
    }

    // Json
    // request -> object mapper -> object -> method -> object mapper -> json -> response
    @PostMapping("/json")
    public User json(@RequestBody User user){
        return user;
    }


    // ResponseEntity
    // Response에 대해서 customizing이 필요하면 ResponseEntity를 사용해야한다.
    @PutMapping("/put")
    public ResponseEntity<User> put(@RequestBody User user) {
        // Response를 보낼 때 http status를 정해주려한다.
        // 그러기 위해서는 ResponseEntity를 이용하여 보내야한다.

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
        // HttpStatus.CREATED는 201을 출력하게 한다.
        // body에는 data를 넣어준다.

    }

}
