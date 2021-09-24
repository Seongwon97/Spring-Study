package com.example.jpa_study.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager; // JPA에서 제공하는 interface

@SpringBootTest
public class EntityManagerTest {
    @Autowired
    private EntityManager entityManager;
    // EntityManager는 JPA에서 제공하는 interface로 spring bean으로 등록되어 있어 Autowired로 사용할 수 있다.

    @Test
    void entityManagerTest() {
        System.out.println(entityManager.createQuery("select u from User u").getResultList());
        // User table을 u라고 부르며 해당 table의 data를 불러와라!
        // userRepository.findAll(); 과 같은 것이다.
        // Query Method, Simple JPA repository는 직접적으로 entityManager를 사용하지 않도록 한번 더 감싸준 것이다.
        // spring jpa에서 제공하지 않는 기능을 사용하거나 특별한 문제가 있어서 별도로 customizing을 해야한다면 entityManager를 직접 받아서 처리한다.
    }
}
