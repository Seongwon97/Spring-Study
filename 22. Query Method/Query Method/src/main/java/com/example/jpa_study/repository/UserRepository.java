package com.example.jpa_study.repository;

import com.example.jpa_study.domain.User;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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
    User getByEmail(String email);
    User readByEmail(String email);
    User queryByEmail(String email);
    User searchByEmail(String email);
    User streamByEmail(String email);
    User findUserByEmail(String email);

    // First, Top을 이름에 붙이면 읽어온 여러 값중에서 첫번째 값만을 return한다.
    List<User> findFirst1ByName(String name);
    List<User> findTop1ByName(String name);
    List<User> findLast1ByName(String name);


    ///////////////////
    ///   And, OR  ///
    //////////////////

    // Query문에서 and, or을 사용하고 싶은 경우 method안에 And or을 넣어준다
    List<User> findByNameAndEmail(String name, String email);
    List<User> findByNameOrEmail(String name, String email);

    //////////////////////
    /////    값 비교  /////
    //////////////////////
    //After와 Before는 시간에 대한 조건을 정할 수 있다.
    // After는 특정 날짜 이후(또는 큰것)에 발생한 것을 조회하게 해주는 method이다.
    // Before는 특정 날짜 이전(또는 작은것)을 조회하게 해주는 method이다.
    List<User> findByCreatedAtAfter (LocalDateTime lastDay);
    List<User> findByIdAfter(Long id);  // input id보다 큰 id를 가진 데이터들을 출력
    // 숫자의 경우도 After, Before를 통해 조회할 수 있다. (하지만 가독성을 위해 날짜에만 사용하는 것을 추천)

    // After/Before말고 GreaterThan/LessThan 으로도 값 제한을 할 수 있다.
    List<User> findByCreatedAtGreaterThan (LocalDateTime yesterday); // 44번 line과 동일한 결과를 출력한다.
    List<User> findByCreatedAtGreaterThanEqual (LocalDateTime yesterday); // ~이상을 나타낼 떄는 Equals를 붙여준다.
    // 일반적으로 After, Before, GreaterThan은 equal를 포함하지않고 초과 미만만 표현한다.

    // Between도 값을 비교해주는 query method로 parameter를 2개를 갖는다.
    // Between은 위의 After, Before, GreaterThan, LessThan과 다르게 해당 값들도 포함한다.(이상, 이하를 의미한다.)
    List<User> findByCreatedAtBetween(LocalDateTime yesterday, LocalDateTime tomorrow);
    List<User> findByIdBetween(Long id1, Long id2);


    //////////////////////
    ///  isEmpty/Null  ///
    //////////////////////
//    List<User> findByIdIsNotNull();  // Id값에 Null값이 없는지?
//    List<User> findByAddressIsNotEmpty();
    // 여기서의 NotEmpty는 String과 같은 문자열이 비어있는지 체크가 아닌 Collection type의 변수가 not empty(비어있는지)를 체크한다.

    //////////////////////
    //////    In    //////
    //////////////////////
    // In절이기 때문에 iterater type인 list가 들어가게 된다.
    // generic type이 들어가야하는 <>에는 name의 type을 넣는다.

    // 일반적으로는 다른 query의 결과값을 다시 query에 넣어야 할 때 사용한다!!!!!
    // in절을 사용할 때는 다른 query문의 결과로 얼마나 많은 데이터들이 나올지 사전에 검토를 하고 사용하는게 좋다.(과부하 걸리는 것을 예방하기 위해서)
    List<User> findByNameIn(List<String> name);



    //////////////////////////////////////////
    ///  StartingWith/EndingWith/Contains  ///
    //////////////////////////////////////////
    // 해당 method들은 문자열에 사용한다.

    List<User> findByNameStartingWith(String name);
    List<User> findByNameEndingWith(String name);
    List<User> findByNameContains(String name);
    List<User> findByNameLike(String name);



    //////////////////////
    //////    Is    //////
    //////////////////////
    // Is는 Is, Equals, 또는 아무런 키워드를 넣지 않으면 Is로 보게된다.
    Set<User> findUserByNameIs(String name);
    Set<User> findUserByName(String name);
    Set<User> findUserByNameEquals(String name);
    // 위의 3개의 method는 모두 parameter의 name과 동등한 이름의 데이터들을 출력하는 method이다.


}
