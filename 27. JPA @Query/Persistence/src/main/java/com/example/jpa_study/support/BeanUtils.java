package com.example.jpa_study.support;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

// void userHistoryTest() 에서 userHistoryRepository이 null값이라
// Spring Bean을 가져오지 못하여 Bean을 가져올 수 있도록 해주는 BeanUtils 클래스 생성


@Component
public class BeanUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanUtils.applicationContext = applicationContext;
    }

    // generic type의 class를 지정해주면 그에 맞는 Bean을 return해준다.
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
}
