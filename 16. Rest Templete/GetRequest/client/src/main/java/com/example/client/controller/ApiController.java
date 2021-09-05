package com.example.client.controller;

import com.example.client.dto.UserResponse;
import com.example.client.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class ApiController {

    private final RestTemplateService restTemplateService;

    public ApiController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }


    // http://localhost:8080/api/client으로 Get request가 오면 getHello 메서드가 실행이되며
    // restTemplateService의 hello 메서드가 호출이된다.
    // hello 메서드의 경우 코드를 보면 Server의 주소인 http://localhost:9090/api/server/hello주소로
    // Get Request를 보내서 결과를 받아온다.
    @GetMapping("")
    public UserResponse getHello(){
        return restTemplateService.hello();
    }
}
