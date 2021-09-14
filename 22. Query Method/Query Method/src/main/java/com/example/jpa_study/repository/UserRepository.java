package com.example.jpa_study.repository;

import com.example.jpa_study.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// JpaRepository를 상속하는 것 만으로도 Jpa의 method들을 사용할 수 있다.
// JpaRepository<> 에서 괄호에는 첫번째에는 Jpa로 사용할 entity(class), 두번째는 해당 class의 pk타입이다.
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
    // Return type은 List, Set, Object등의 여러 타입으로 할 수 있으며
    // JPA가 데이터를 읽어오고 return type에 맞춰서 데이터를 return해준다.
    // 단 데이터가 여러개인데 User와 같이 단일 객체로 return하면 오류가 발생한다.
}
