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
    } // 현재 상태는 영속성 context안에서 관리되지 않은 new상태이다.



    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void put2() {
        User user = new User();
        user.setName("newUser");
        user.setEmail("newUser@naver.com");

        entityManager.persist(user); // user entity를 managed상태로 보낸다.
        entityManager.detach(user); // detach를 하면 해당 entity는 영속성 context에서 더이상 관리하지 않는다. (User는 준 영속상태가 된다.)
        user.setName("newUserAfterPersist");
        entityManager.merge(user); // detach를 하여 준 영속상태에 빠진 entity를 merge를 하면 다시 영속 상태가 된다.
        entityManager.flush();
        entityManager.clear(); // 영속 상태에 있는 entity를 모두 뺴낸다. (대기 상태에 있는 변경 데이터들도 삭제)
                                // 그래서 clear를 할 때는 하기전에 flush를 해야한다.

        User user1 = userRepository.findById(1L).get();
        entityManager.remove(user1);

    }
    // 객체가 영속성 context에서 관리된다면 객체의 변화를 별도로 save를 통해
    // DB에 저장해주지 않더라도 자동으로 DB에 반영시켜준다.
    // 그리하여 entityManager.persist(user); 이후의 줄에서 객체 값을 변경해도 DB에 반영이 된다.

    // 이러한 기능을 영속성 context가 제공하는 dirty check라고 한다.
    // 영속성 context에서 관리하는 객체들은 처음 실행될 때 일종의 snapshot으로 저장하여 관리를 한다.
    // 그 후에 flush, commit되는 시점, jpa query가 실행되는 시점에 이전에 저장한 snapshot과 현재 entity의 값을 비교하고
    // 변경된 값이 있으면 변경해준다.

}
