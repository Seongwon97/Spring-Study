package com.example.jpa_study.repository;

import com.example.jpa_study.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void crud() { // create, read, update, delete

        ////////////////////////
        ////  Update Query  ////
        ///////////////////////
        userRepository.save(new User("david", "david@naver.com"));
        User user = userRepository.findById(3L).orElseThrow(RuntimeException::new);
        user.setEmail("martin-updated@gmail.com");

        userRepository.save(user);
    }

    @Test
    void select() {
        // UserRepository에 위치한 findByName 메서드를 List로 안받고 단일 객체인 User로 받으면
        // 해당 코드를 실행하였을 때 martin의 이름을 가진 사람이 2명이라 NonUniqueResultException이 발생한다.
//        System.out.println(userRepository.findByName("martin"));
//
//        System.out.println("findByEmail : "+userRepository.findByEmail("martin@gmail.com"));
//        System.out.println("getByEmail : "+userRepository.getByEmail("martin@gmail.com"));
//        System.out.println("readByEmail : "+userRepository.readByEmail("martin@gmail.com"));
//        System.out.println("queryByEmail : "+userRepository.queryByEmail("martin@gmail.com"));
//        System.out.println("searchByEmail : "+userRepository.searchByEmail("martin@gmail.com"));
//        System.out.println("streamByEmail : "+userRepository.streamByEmail("martin@gmail.com"));
//        System.out.println("findUserByEmail : "+userRepository.findUserByEmail("martin@gmail.com"));
//
//        System.out.println("findTop1ByName : " + userRepository.findTop1ByName("martin"));
//        System.out.println("findFirst1ByName : " + userRepository.findFirst1ByName("martin"));


//        System.out.println("findByEmailAndName : " + userRepository.findByNameAndEmail("martin", "martin@gmail.com"));
//        System.out.println("findByEmailOrName : " + userRepository.findByNameOrEmail("James", "martin@gmail.com"));
//        System.out.println("findByCreatedAtAfter : " + userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L)));
//        System.out.println("findByIdAfter : " + userRepository.findByIdAfter(4L));
//        System.out.println("findByCreatedAtGreaterThan : " + userRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)));
//        System.out.println("findByCreatedAtGreaterThanEqual : " + userRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)));
//        System.out.println("findByCreatedAtBetween : " + userRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now().plusDays(1L)));
//        System.out.println("findByIdBetween : " + userRepository.findByIdBetween(3L, 6L));
//        System.out.println("findByIdIsNotNull : " + userRepository.findByIdIsNotNull());
//        System.out.println("findByAddressIsNotEmpty : " + userRepository.findByAddressIsNotEmpty());
        System.out.println("findByNameIn : " + userRepository.findByNameIn(Lists.newArrayList("martin", "James")));
        System.out.println("findByNameStartingWith : " + userRepository.findByNameStartingWith("mar"));
        System.out.println("findByNameEndingWith : " + userRepository.findByNameEndingWith("tin"));
        System.out.println("findByNameContains : " + userRepository.findByNameContains("rti"));
        System.out.println("findByNameLike : " + userRepository.findByNameLike("%rti%"));
        // contains("rti")와 like("%rti%")는 같은 것이다
        // 하지만 가독성으로는 Contains가 훨씬 좋다.
    }

    @Test
    void pagindAndSortingTest() {
        //////////////////////
        ////    Sorting   ////
        //////////////////////

        System.out.println("findTop1ByName : "+ userRepository.findTop1ByName("martin"));
        System.out.println("findLast1ByName : "+ userRepository.findLast1ByName("martin"));
        // 실행 결과를 보면 LastBy~ 는 keyword가 원래 없어서 Limit이 적용되지 않고 where조건만 적용되게 되었다. (name이 martin인 사람 모두 출력)

        System.out.println("findTop1ByNameOrderByIdDesc : "+ userRepository.findTop1ByNameOrderByIdDesc("martin")); // 우리가 원하는 LastBy의 결과
        System.out.println("findFirstByNameOrderByIdDescEmailAsc : "+ userRepository.findFirst2ByNameOrderByIdDescEmailAsc("martin"));

        // Sort Parameter를 사용하여 정렬하는 경우
        // -> 위와 같은 방법으로 Sorting을 하면 조건이 많아질수록 메소드의 이름 길이도 길어지며 코드의 가독성에도 그리 좋지 않다.
        // -> 또한 조건에 따라 여러 method를 만들어야하는데 Sort method를 사용할 경우 하나의 method로 여러 sort조건을 줘서 사용할 수 있다.
        // -> 그래서 아래와 같이 Sorting Method를 사용하는 것을 추천한다.
        // -> 하지만 반대로 같은 Sorting방식으로 사용되는 경우가 많은 경우 위의 Keyword를 사용한 method를 사용하는게 가독성 측면에서 더 좋을 수 있다.
        // -> 개발자는 같은 결과를 내더라도 여러 코딩 방식이 있다는 것을 인지하고 어떤 것이 가독성이 더 좋을지 생각하며 코딩해야한다.
        // 한가지 조건을 사용하는 경우
        System.out.println("findFirstByName with Sort parameter : "+ userRepository.findFirstByName("martin", Sort.by(Sort.Order.desc("id"))));
        // 두가지 조건을 사용하는 경우
        System.out.println("findFirstByName with Sort parameter : "+ userRepository.findFirstByName("martin", Sort.by(Sort.Order.desc("id"), Sort.Order.asc("email"))));
        // 코드의 가독성을 높이기 위해서는 아래와 같이 sort method를 만들고 호출을 하며 사용할 수도 있다.
        System.out.println("findFirstByName with Sort parameter : "+ userRepository.findFirstByName("martin", getSort()));

    }

    private Sort getSort() {
        return Sort.by(
                Sort.Order.desc("id"),
                Sort.Order.asc("email"),
                Sort.Order.desc("createdAt"),
                Sort.Order.desc("updatedAt")
        );
    }
}