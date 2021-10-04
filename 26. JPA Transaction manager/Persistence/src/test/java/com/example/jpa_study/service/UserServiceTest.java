package com.example.jpa_study.service;

import com.example.jpa_study.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void test1() {
        userService.put1();
        System.out.println(">>> "+userRepository.findByEmail("newUser@naver.com"));
        // UserService.java의 put에서 생성된 객체는 DB에 저장하는게 아니라
        // 'new상태'의 객체로써 비 영속상태라 newUser@naver.com을 탐색했을 때
        // 값을 찾지 못하고 null값이 나오게 된다.
    }




    @Test
    void test2() {
        userService.put2();
        System.out.println(">>> "+userRepository.findByEmail("newUser@naver.com"));
        // entityManager를 사용한 것과 같이 객체가 영속성 context의 관리를 받게되면
        // 객체의 변화된 값이 자동으로 반영된다.
    }
}