package com.example.aop.aop;
// 특정 method의 실행 시간을 찍어주는 Aop

import com.example.aop.annotation.Timer;
import jdk.jshell.spi.ExecutionControl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimerAop {
    @Pointcut("execution(* com.example.aop.controller..*.*(..))") // controller하위에 있는 것들에 제약을 걸 것이다.
    private void cut() {}

    @Pointcut("@annotation(com.example.aop.annotation.Timer)") // timer annotation에 제약을 걸 것이다.
    private void enableTimer(){}

    // before, after가 있으면 time을 공유할 수 없다. 이럴때는 around를 이용한다.
    @Around("cut() && enableTimer()") // cut과 enableTimer조건을 같이 쓴다.
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed(); // 실질적인 수행이라 이 실행 전후로 timer를 측정하면 된다.
        // joint point의 proceed를 하면 실질적인 method가 실행되며 return type이 있으면 object로 return한다.

        stopWatch.stop();

        System.out.println("Total time : "+ stopWatch.getTotalTimeSeconds());
    }
}
