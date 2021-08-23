package com.example.api_test_post.controller;

import dto.PostRequestDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostApiController {

    @PostMapping("/post")
    public void post(@RequestBody Map<String, Object> requestData){
        // Post도 get과 같이 parameter앞에 무언가를 붙여줘야한다. Get은 @RequestParam을 붙였다면
        // Post는 @RequestBody를 붙여준다.
        requestData.forEach((key, value) -> {
            System.out.println("key : " + key);
            System.out.println("value : " + value);
        });
    }

    // Map형식 말고 DTO(class)를 통해 Post
    @PostMapping("/post1")
    public void post1(@RequestBody PostRequestDTO requestData){
        // get과 다르게 객체를 사용하여 받아와도 RequestBody를 입력해줘야한다.
        System.out.println(requestData);
    }
}
