package com.example.springioc;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


// Spring application이 실행이 될 때
// set method를 통해 application context를 주입을 해주고 우리는 그것을 받아 static변수에 할당한다.
// 그 이후 가져다 사용하면 된다.
@Component
public class ApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContext context;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static ApplicationContext getContext(){
        return context;
    }
}
