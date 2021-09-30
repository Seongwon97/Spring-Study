package com.example.jpa_study.service;

import com.example.jpa_study.entity.User;
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
    } // 현재 상태는 영속성 context안에서 관리되지 않은 new상태이다.



    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void put2() {
        User user = new User();
        user.setName("newUser");
        user.setEmail("newUser@naver.com");

        entityManager.persist(user);
        user.setName("newUserAfterPersist");

    }
    // 객체가 영속성 context에서 관리된다면 객체의 변화를 별도로 DB에 저장해주지 않더라도
    // 자동으로 DB에 반영시켜준다.
    // 그리하여 entityManager.persist(user); 이후의 줄에서 객체 값을 변경해도 DB에 반영이 된다.

}
