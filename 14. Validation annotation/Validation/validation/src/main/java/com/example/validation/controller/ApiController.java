package com.example.validation.controller;

import com.example.validation.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {
//    @PostMapping("/user")
//    public User user(@RequestBody User user){
//        System.out.println(user);
//        return user;
//    }


    // 예전에 사용하였던 validation으로 request때 들어오는 데이터 타입이 잘못되면
    // bad request를 보내는 식으로 오류를 발생시켰다.
    // 하지만 이러한 방식은 여러개의 data에 대해 하나씩 조건을 걸기에는 코드의 복잡도가 늘어나 요즘은 사용하지 않는다.
    // -> 대신 validation annotation을 사용한다.
    /*
    @PostMapping("/user")
    public ResponseEntity user(@RequestBody User user){
        if(user.getAge() >= 90)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
        return ResponseEntity.ok(user);
    }
    */

    // validation annotation을 사용하면 아래와 같이 parameter에 @Valid를 붙여줘야한다.
    // -> 받아들이는 객체의 @Email과 같은 annotation을 검사하고 형식에 맞지 않으면 error를 발생시킨다.
    @PostMapping("/user")
    public ResponseEntity user(@Valid @RequestBody User user, BindingResult bindingResult){
        // BindingResult를 parameter로 넣어 에러가 발생 시 해당 객체에 에러 내용이 저장되도록 설정?
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError) objectError;
                String message = objectError.getDefaultMessage();

                System.out.println("Field : "+ field.getField());
                System.out.println(message);

                sb.append("Field : "+ field.getField());
                sb.append("message: "+message);
                sb.append("\n\n");
            });
            // 조건에 맞지 않는다면 Badrequest와 error출력
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }

        return ResponseEntity.ok(user);
    }
}
