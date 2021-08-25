package com.example.aop.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect // AOP로 동작하려면 Aspect를 추가해야한다.
@Component // Spring에서 관리하게 한다.
public class ParameterAop {

    // @Pointcut과 같은 annotation이 많이 있음, 찾아 볼 것.
    //com.example.aop.controller하위에 있는 모든 것을 AOP로 보겠다~
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut() {

    }

    @Before("cut()") // cut method가 실행되기 전에 해당 method를 실행시키겠다.
    public void before(JoinPoint joinPoint) {
        // Joinpoint는 들어가는 지점에 대한 정보를 갖을 수 있는 객체이다.

        // Method 이름 출력
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println(method.getName());

        Object[] args = joinPoint.getArgs(); // method에 들어가는 매개변수들의 배열열
        for(Object obj : args){
            System.out.println("type : "+ obj.getClass().getSimpleName());
            System.out.println("type : "+ obj);
        }
    }

    // 반환도 Cut이후 return을 할 때 실행하겠다.
    // returning에는 내가 받고싶은 객체의 이름을 넣어준다. (이는 method의 parameter명과 동일해야한다.)
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj) {
        System.out.println("Return obj");
        System.out.println(returnObj);
    }

}
