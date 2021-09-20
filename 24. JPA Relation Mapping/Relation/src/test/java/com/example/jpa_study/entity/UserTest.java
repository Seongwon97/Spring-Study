package com.example.jpa_study.entity;

import org.junit.jupiter.api.Test;

class UserTest {
    @Test
    void test() {
        User user = new User();
        user.setEmail("seongwon@gmail.com");
        user.setName("seongwon");

        System.out.println(">>> " + user);
    }
}