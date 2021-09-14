package com.example.jpa_study.repository;

import com.example.jpa_study.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void crud() { // create, read, update, delete

        ////////////////////////
        ////  Update Query  ////
        ///////////////////////
        userRepository.save(new User("david", "david@naver.com"));
        User user = userRepository.findById(3L).orElseThrow(RuntimeException::new);
        user.setEmail("martin-updated@gmail.com");

        userRepository.save(user);
    }

    @Test
    void select(){
        // UserRepository에 위치한 findByName 메서드를 List로 안받고 단일 객체인 User로 받으면
        // 해당 코드를 실행하였을 때 martin의 이름을 가진 사람이 2명이라 NonUniqueResultException이 발생한다.
        System.out.println(userRepository.findByName("martin"));
    }
}