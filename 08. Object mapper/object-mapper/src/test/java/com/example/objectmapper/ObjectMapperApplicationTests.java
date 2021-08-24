package com.example.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ObjectMapperApplicationTests {

    @Test
    void contextLoads() throws JsonProcessingException {
        System.out.println("-------------");

//        Object mapper는 Text형태의 JSON을 object형태로 바꿔주고
//        반대로 Object를 Text형태의 Json으로 변경해주기도 한다.
//
//        이전까지 우리가 request로 json형식의 text가 오면 object로 변경해주었고
//        response를 object를 json으로 변경해주며 보내는 것이 Object mapper의 예시이다.



        // 직접 객체를 생성해서 활용할 수 있는 방법
        var objectMapper = new ObjectMapper(); // object mapper객체 생성

        // object -> text
        // Object를 text로 바꿀 때는 get method를 활용하여 객체에는 getter가 필요하다
        // 주의사항: getter를 제외한 method에는 get이라는 이름이 빠져야한다.
        var user = new User("Steve", 20);
        // value를 String으로 변경해줌
        var text = objectMapper.writeValueAsString(user);
        System.out.println(text);


        // text -> object
        // text에서 object로 바꿀때는 object의 default 생성자가 필요하다.
        var objectUser = objectMapper.readValues(text, User.class);
        System.out.println(objectUser);
    }

}
