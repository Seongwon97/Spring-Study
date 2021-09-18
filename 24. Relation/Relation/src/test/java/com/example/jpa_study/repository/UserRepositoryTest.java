package com.example.jpa_study.repository;

import com.example.jpa_study.entity.Gender;
import com.example.jpa_study.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @Test
    void insertAndUpdateTest(){
        User user = new User();
        user.setName("martin");

        userRepository.save(user);

        User user2 = userRepository.findById(6L).orElseThrow(RuntimeException::new);
        user2.setName("marrrrrrtin");

        userRepository.save(user2);

    }

    @Test
    void enumTest() {
        User user = userRepository.findById(3L).orElseThrow(RuntimeException::new);
        user.setGender(Gender.MALE);

        userRepository.save(user);

        userRepository.findAll().forEach(System.out::println);

        System.out.println(userRepository.findRawRecord().get("gender"));
    }

    @Test
    void listenerTest() {
        User user = new User();
        user.setName("martin");
        user.setEmail("martin@gmail.com");

        userRepository.save(user);

        User user2 = userRepository.findById(3L).orElseThrow(RuntimeException::new);
        user2.setName("marrrrrrtin");

        userRepository.save(user2);

        userRepository.deleteById(4L);
    }


    // 실제로 prePersist가 사용되는 예시 설명 + Test
    // DB를 설계하다보면 보통 Created time과 Updated time을 column을 만들어 저장한다.
    // 하지만 데이터를 생성할 때마다 user.setCreatedAt(LocalDateTime.now());와 같이 직접 넣어주다보면 잊어버리는 일도 발생할 것이다.
    // 그래서 prePersist를 사용하여 자동으로 Created time과 Updated time값을 넣어준다.
    @Test
    void prePersistTest() {
        User user = new User();
        user.setName("Seongwon");
        user.setEmail("seongwon@gmail.com");
//        user.setCreatedAt(LocalDateTime.now());
//        user.setUpdatedAt(LocalDateTime.now());


        userRepository.save(user);

        System.out.println(userRepository.findByEmail("seongwon@gmail.com"));
    }

    @Test
    void preUpdateTest() {
        User user = userRepository.findById(3L).orElseThrow(RuntimeException::new);
        System.out.println("Before Update : " + user);

        user.setName("Seongwon");
        userRepository.save(user);

        System.out.println("After Update : " + userRepository.findAll().get(0));
    }

    @Test
    void userHistoryTest() {
        User user = new User();
        user.setEmail("Seongwon@gmail.com");
        user.setName("Seongwon");

        userRepository.save(user);
        // 새로운 user data를 생성한 후 데이터를 업데이트 하여 다시 저장

        user.setName("Seongwon_new");

        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);
    }
}