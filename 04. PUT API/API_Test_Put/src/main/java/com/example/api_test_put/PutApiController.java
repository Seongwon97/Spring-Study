package com.example.api_test_put;

import dto.PostRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PutApiController {

    // 반환값 없이 받기만 하는 경우
    @PutMapping("/put1")
    public void put1(@RequestBody PostRequestDto requestDto){
        System.out.println(requestDto);
    }

    // 반환값으로 받은 데이터를 다시 json으로 보내는 경우
    @PutMapping("/put2")
    public PostRequestDto put2(@RequestBody PostRequestDto requestDto){
        System.out.println(requestDto);
        return requestDto;
    }

    // pathVariable을 쓰는 경우 (복습)
    @PutMapping("/put3/{userId}")
    public PostRequestDto put3(@RequestBody PostRequestDto requestDto, @PathVariable(name="userId") Long id){
        System.out.println(requestDto);
        return requestDto;
    }
}
