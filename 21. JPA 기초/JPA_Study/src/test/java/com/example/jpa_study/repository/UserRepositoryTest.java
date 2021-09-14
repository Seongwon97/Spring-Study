package com.example.jpa_study.repository;

import com.example.jpa_study.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void crud() { // create, read, update, delete
        userRepository.save(new User());
        //save 메서드는 새로운 entity를 넣어준느 메서드이다.
        userRepository.save(new User());

        System.out.println(">>> "+ userRepository.findAll());
        // findAll() 메서드는 해당 repository(table)에 있는 데이터들을 모두 가져오는 메서드이다.
        // 실제 서비스에서는 성능적인 이슈가 있어서 잘 사용하지 않는다. (데이터가 많으면 과부하걸린다.)

    }
}