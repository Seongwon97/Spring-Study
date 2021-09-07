package com.example.repositoryinterface.repository;

import com.example.repositoryinterface.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;


    @Test
    void crud() {
        userRepository.save(new User());

        //userRepository.findAll().forEach(System.out::println);
    }
}