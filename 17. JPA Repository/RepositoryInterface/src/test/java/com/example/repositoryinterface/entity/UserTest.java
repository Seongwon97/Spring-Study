package com.example.repositoryinterface.entity;

import com.example.repositoryinterface.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


class UserTest {
    @Test
    void test() {
        User user = new User();
        user.setEmail("martin@naver.com");
        user.setName("martin");

        System.out.println(user.getName());
    }
}