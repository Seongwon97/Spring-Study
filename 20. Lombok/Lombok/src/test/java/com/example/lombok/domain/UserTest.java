package com.example.lombok.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void test() {
        User user = new User();
        user.setEmail("seongwon@gmail.com");
        user.setName("seongwon");

        System.out.println(">>> " + user);
    }
}