package com.example.swagger.controller;

import com.example.swagger.dto.UserReq;
import com.example.swagger.dto.UserRes;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

// Controller의 보이는 이름 설정
@Api(tags = {"API 정보를 제공하는 Controller"})
@RestController
@RequestMapping("/api")
public class ApiController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    // 아래 코드에서 ApiParam으로 각각의 매개변수에 정보를 담았는데 아래 주석처리된 코드와 같이 위에서 annotation으로 한번에 지정가능하다
//    @ApiImplicitParams(
//            {@ApiImplicitParam(name = "x", value = "x값", required = true, dataType = "int", paramType = "path"),
//                    @ApiImplicitParam(name = "y", value = "y값", required = true, dataType = "int", paramType = "query")}
//    )
    @GetMapping("/plus/{x}")
    public int plus(
            @ApiParam(value = "x값") // API가 붙은 annotation은 swagger에서 보이는 이름을 표현
            @PathVariable int x,

            @ApiParam(value = "y값")
            @RequestParam int y) {
        return x+y;
    }

    @ApiResponse(code = 502, message = "사용자의 나이가 10살 이하일때") // HTTP Status code를 추가하거나 설명을 넣는법
    @ApiOperation(value = "사용자의 이름과 나이를 리턴하는 메소드") // method설명을 붙여주는 방법
    @GetMapping("/userG")
    public UserRes userG(UserReq userReq){
        return new UserRes(userReq.getName(), userReq.getAge());
    }


    @PostMapping("/userP")
    public UserRes userP(@RequestBody UserReq userReq){
        return new UserRes(userReq.getName(), userReq.getAge());
    }
}
