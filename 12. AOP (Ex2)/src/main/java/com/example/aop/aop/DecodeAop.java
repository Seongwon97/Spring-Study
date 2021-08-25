package com.example.aop.aop;

import com.example.aop.dto.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;


// 암호화된 메세지를 받아서 프로그램 내에서만 암호를 풀어 평문을 사용하고
// 다시 return을 할때는 암호화하여 return하는 코드

@Aspect
@Component
public class DecodeAop {
    @Pointcut("execution(* com.example.aop.controller..*.*(..))") // controller하위에 있는 것들에 제약을 걸 것이다.
    private void cut() {}

    @Pointcut("@annotation(com.example.aop.annotation.Decode)") // timer annotation에 제약을 걸 것이다.
    private void enableDecode(){}

    @Before("cut() && enableDecode()")
    public void before(JoinPoint joinPoint) throws UnsupportedEncodingException {

        Object[] args = joinPoint.getArgs(); // method에 들어가는 매개변수들의 배열
        for(Object arg : args){
            if(arg instanceof User){ // argument에 내가 원하는 User가 매칭이 되면
                User user = User.class.cast(arg); // arg를 User class로 형변환

                String base64Email = user.getEmail();  // 기존에 encoding되어있던 email을 꺼내고
                String email = new String(Base64.getDecoder().decode(base64Email), "UTF-8");
                // 그것을 다시 Decoding시켜서 set을 시킨다.
                user.setEmail(email);

            }
        }
    }

    @AfterReturning(value = "cut() && enableDecode()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj){
        if(returnObj instanceof User){
            User user = User.class.cast(returnObj); // arg를 User class로 형변환

            String email = user.getEmail();  // 기존에 encoding되어있던 email을 꺼내고
            String base64Email = Base64.getEncoder().encodeToString(email.getBytes( ));
            // 그것을 다시 Decoding시켜서 set을 시킨다.
            user.setEmail(base64Email);
        }
    }
}
