package com.example.springioc;

import org.springframework.stereotype.Component;

import java.util.Base64;

// @Component을 붙이면 해당 class가 Spring Bean에 등록시켜준다. (Bean은 본인이 갖고 있는 Java 객체를 의미)
// Spring Bean에 등록하며 application에 제어를 넘겼다, 이것을 IOC(제어의 역전)이라고 한다.
// @Component("name")을 통해 bean에 저장될 이름을 지정할 수도 있다. 기본적으로는 class의 이름으로 저장
@Component
public class Base64Encoder implements IEncoder{

    public String encode(String message){
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}
