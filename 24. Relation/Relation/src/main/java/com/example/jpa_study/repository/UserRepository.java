package com.example.jpa_study.repository;

import com.example.jpa_study.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

// JpaRepository를 상속하는 것 만으로도 Jpa의 method들을 사용할 수 있다.
// JpaRepository<> 에서 괄호에는 첫번째에는 Jpa로 사용할 entity(class), 두번째는 해당 class의 pk타입이다.
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
    List<User> findByEmail(String email);

    // @Query annotatoin으로 value에 query문을 적고 nativeQuery = true를 해주면 해당 query가 실행되는 method가 된다.
    @Query(value = "select * from user limit 1;", nativeQuery = true)
    Map<String, Object> findRawRecord();

}
