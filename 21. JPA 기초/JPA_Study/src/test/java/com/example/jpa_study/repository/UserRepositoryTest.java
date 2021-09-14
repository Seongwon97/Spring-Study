package com.example.jpa_study.repository;

import com.example.jpa_study.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void crud() { // create, read, update, delete
        //userRepository.save(new User());
        //save 메서드는 새로운 entity를 넣어준느 메서드이다.

        //System.out.println(">>> "+ userRepository.findAll());
        // findAll() 메서드는 해당 repository(table)에 있는 데이터들을 모두 가져오는 메서드이다.
        // 실제 서비스에서는 성능적인 이슈가 있어서 잘 사용하지 않는다. (데이터가 많으면 과부하걸린다.)

        //userRepository.findAll().forEach(System.out::println);

        ////////////////////////
        ////   호출 예제!!!  ////
        ///////////////////////
        // name의 내림차순으로 정렬예제 (내부적으로 알아서 Query를 짜서 요청한다.)
        //List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));

        // ID가 1,3,5인 것을 검색 (L은 id를 long type으로 지정해서 붙인 것이다.)
        //List<User> users = userRepository.findAllById(Lists.newArrayList(1L, 3L, 5L));

        //users.forEach(System.out::println);


        ////////////////////////
        ////   저장 예제!!!  ////
        ///////////////////////
        // data.sql에서 call next value for hibernate_sequence:이 먹히지 않아 id값 때문에 저장이 잘 안됨..
//        User user1 = new User("Jack", "jack@gmail.com");
//        User user2 = new User("Mike", "mike@gmail.com");
//
//        userRepository.saveAll(Lists.newArrayList(user1, user2));
//
//        List<User> users = userRepository.findAll();
//        users.forEach(System.out::println);

        ////////////////////////
        ////   getOne 예제  ////
        ///////////////////////
        // getOne은 Lazy한 메소드이기에 method위에 @Transactional를 붙여줘야한다.
//        User user = userRepository.getOne(1L);
//        System.out.println(user);

//        User user = userRepository.findById(1L).orElse(null);;
//        System.out.println(user);

        // -> findById는 eager한 patch를 하여 읽어오는 것 까지 한번에 하지만
        // getOne은 lazy한 patch를 하여 값을 읽어오지 않고 가져오기만 한다.
    }
}