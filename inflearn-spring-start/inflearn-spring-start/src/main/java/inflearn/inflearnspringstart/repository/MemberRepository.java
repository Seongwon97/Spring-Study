package inflearn.inflearnspringstart.repository;

import inflearn.inflearnspringstart.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    // Optional은 값을 return받았을 때 null인 상황이 발생할 수 있어
    // 이를 감싸주기 위해 사용한다. (요즘은 Optional을 사용한다고 함)
    List<Member> findAll();
}
