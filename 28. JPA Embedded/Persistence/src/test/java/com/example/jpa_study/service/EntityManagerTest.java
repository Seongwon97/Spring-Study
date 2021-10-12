package com.example.jpa_study.service;

import com.example.jpa_study.entity.User;
import com.example.jpa_study.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager; // JPA에서 제공하는 interface
import javax.transaction.Transactional;

@SpringBootTest
@Transactional // 하나의 Transaction으로 실행시킴
public class EntityManagerTest {
    @Autowired
    private EntityManager entityManager;
    // EntityManager는 JPA에서 제공하는 interface로 spring bean으로 등록되어 있어 Autowired로 사용할 수 있다.

    @Autowired
    private UserRepository userRepository;

    @Test
    void entityManagerTest() {
        System.out.println(entityManager.createQuery("select u from User u").getResultList());
        // User table을 u라고 부르며 해당 table의 data를 불러와라!
        // userRepository.findAll(); 과 같은 것이다.
        // Query Method, Simple JPA repository는 직접적으로 entityManager를 사용하지 않도록 한번 더 감싸준 것이다.
        // spring jpa에서 제공하지 않는 기능을 사용하거나 특별한 문제가 있어서 별도로 customizing을 해야한다면 entityManager를 직접 받아서 처리한다.
    }


    // 영속성 Context안에서 entity를 관리하는 EntityManager는 Cache를 갖고 있다.
    // 우리가 Save 메서드를 실행하였을 때는 바로 DB가 업데이트 되지 않고 그 사이에 Cache를 거쳐서 DB가 업데이트된다. 그래서 Cache가 중요
    @Test
    void cacheFindTest() {
        // 아래의 같은 data를 id값으로 반복적으로 찾게 되었을 때 @Transactional이 붙는다면 JPA는 데이터를 조회하며 cache에 저장을 한 후
        // 다음 조회때는 같은 조회를 하지 않고 cache에 있는 값을 내보낸다. (이것이 1차 cache)
        // 1차 cache는 Map의 형태로 만들어진다. (key는 id값, value는 해당 entity값이 들어있다.)
        // key값이 id라서 id로 조회를 하게 되면 영속성 context에 있는 1차 cache에 entity가 있는지 확인을 해보고 값이 있다면 DB조회없이 return한다.
        // 값이 없으면 쿼리문으로 조회를 하고 1차 cache에 저장후 return해준다.

        // 즉, 하나의 Transactional에서 id값으로 조회하는 데이터들은 1차 cache에 저장을 하여 관리를 함으로써 성능 저하를 방지한다.
        System.out.println(userRepository.findById(1L).get());
        System.out.println(userRepository.findById(1L).get());
        System.out.println(userRepository.findById(1L).get());
    }

    @Test
    void cacheFindTest2() {
        // @Transactional이 없다면 아래의 코드는 name, email의 값을 변경하고 save할 때 마다 업데이트 될 것이다.(업데이트 2번)
        // @Transactional이 있다면 변경 값을 저장을 해뒀다가 merge한 뒤에 마지막에 한번에 update한다. (업데이트 1번)
        User user = userRepository.findById(1L).get();
        user.setName("mariiiin");

        userRepository.save(user);
        // save의 경우 method 정의를 보면 @Transactional이 걸려 있어서
        // 전체 logic에 대해 @Transactional을 걸지 않으면 save를 할 때마다 자동으로 flush가 일어나 DB에 값이 저장된다.

        user.setEmail("marrrrin@gmail.com");

        userRepository.save(user);
        userRepository.flush();
        // flush는 영속성cache에 쌓여 아직 반영되지 않은 것들을 DB에 반영시켜준다.
        // flush를 자주 쓰게 되면 영속성 cache의 장점을 무효화시키게 되어서 적당히 사용하는게 좋다.

        // 영속성 context의 값이 DB에 반영되는 경우
        // 1. flush를 통해 개발자가 직접 반영하는 경우
        // 2. Transaction이 끝나서 해당 query가 commit되는 시점
        // 3. 복잡한 조회 조건에 JPQL query가 실행되는 시점
        // (위에서 데이터를 업데이트하고 아래에서 findAll을 하였을 때는 JPA에서 findAll을 하기 전에 flush를 시키고 조회를 하게된다.)
    }
}
