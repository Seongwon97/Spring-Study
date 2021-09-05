package com.example.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 이전에는 Spring boot에서 자동으로 Exception을 하였으나
// 아래와 같이 Exception 처리를 추가하여 아래 코들 Exception을 관리한다.
@RestControllerAdvice // 이렇게 지정하면 global하게 모든 공간에서 발생하는 exception을 모두 받아서 처리해준다.
public class GlobalControllerAdvice {

    // RestAPI이기 떄문에 ResponseEntity로 return
    @ExceptionHandler(value = Exception.class) // 모든 Exception을 다 받는다.
    public ResponseEntity exception(Exception e) { // Error를 매개변수로 받는다.
        System.out.println(e.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    // 이러한 exceptionHandler method를 ApiController.java 파일과 같은 controller파일에 넣는다면
    // Global하게가 아닌 해당 controller에만 동작하는 ExceptionHandler가 된다.
    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

    }
}
