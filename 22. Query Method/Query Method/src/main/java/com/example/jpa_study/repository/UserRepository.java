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


    // 아래의 method들은 모두 같은 일으 하는 method들이다.
    // 이름은 find(get,ready,,,,) By가 핵심이며
    // 두 문자 사이 또는 뒤에 구분할 수 있는 다른 문자를 넣어 구분하며 만들어주면 된다.
    // 그러면 자동으로 Select Qeury Method가 만들어진다.
    // 이중에서 자신이 가독성이 높다고 생각되는 것을 이용하면 된다.
    User findByEmail(String email);
    User getByByEmail(String email);
    User readByEmail(String email);
    User queryByEmail(String email);
    User searchByEmail(String email);
    User streamByEmail(String email);
    User findUserByEmail(String email);

    // First, Top을 이름에 붙이면 읽어온 여러 값중에서 첫번째 값만을 return한다.
    List<User> findFirst1ByName(String name);
    List<User> findTop1ByName(String name);
    List<User> findLast1ByName(String name);
}
