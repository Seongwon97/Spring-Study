package com.example.jpa_study.service;

import com.example.jpa_study.entity.User;
import com.example.jpa_study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
public class UserService {
    @Transactional
    public void put1() {
        User user = new User();
        user.setName("newUser");
        user.setEmail("newUser@naver.com");
    }



    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void put2() {
        User user = new User();
        user.setName("newUser");
        user.setEmail("newUser@naver.com");

        entityManager.persist(user);
        entityManager.detach(user);
        user.setName("newUserAfterPersist");
        entityManager.merge(user);
        entityManager.flush();
        entityManager.clear();

        User user1 = userRepository.findById(1L).get();
        entityManager.remove(user1);

    }


}
