package com.example.jpa_study.repository;

import com.example.jpa_study.entity.Book;
import com.example.jpa_study.repository.dto.BookNameAndCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Modifying
    @Query(value = "update book set category='none'", nativeQuery = true)
    void update();
    // 위의 코드들을 custom query라고 한다.

    List<Book> findByCategoryIsNull();

    List<Book> findAllByDeletedFalse();

    List<Book> findByCategoryIsNullAndDeletedFalse();

    // 최신 데이터 가져오기
    // value안에 들어가는 문법은 sql이 아니라 jpql이라는 문법이 들어간다. -> JPA의 entity를 기반으로 하는 쿼리를 생성해주기 위한 문법이다.
    // 그래서 table이름인 book이 아닌 entity이름인 Book이 들어가게된다.
    // where에 있는 name, createdAt 등은 메서드의 파라미터가 아니고 엔티티 내에 있는 field의 이름을 가르킨다.
    // JPQL은 query method를 만드는 것과 비슷한 과정에서 만들어진다~

    // JPQL문을 만들며 where문에는 parameter가 필요할 텐데 이러한 경우 두가지 방법으로 할 수 있다.

    // 첫번째 방법 : 아래와 같이 ?1, ?2, ?3과 같이 넣어준다. ?1, ?2 등이 의미하는 것은 메서드의 첫번째 파라미터, 두번째 파라미터를 가르킨다. (index가 1부터 시작함)
    // -> ?1, ?3, ?2처럼 꼭 파라미터의 순서대로 사용하지 않아도 되는데 자바에서는 순서에 의존성을 가지는 프로그래밍을 지양하여 가능하면 순서대로 하는게 좋다.
    // 만약 코딩 중간에 파라미터를 기존 파라미터들 사이에 추가를 하게 된다면 뒤의 순서는 다 꼬여서 오류가 발생할 수도 있다.
    @Query(value = "select b from Book b " +
            "where name =?1 and createdAt >=?2 and updatedAt >= ?3 and category is null") // book을 b라고 하고 b를 가져온다~
    List<Book> findByNameRecently1(String name, LocalDateTime createdAt, LocalDateTime updatedAt);

    // 두번째 방법은 아래와 같이 메서드의 파라미터들을 @Param을 통해 이름을 정해주고 where문에서는 :name, ?createdAt과 같이 넣어주는 방법이다.
    // 해당 방법은 parameter순서에 영향을 받지 않기 때문에 logic의 변경에 더 자유롭고 부작용에서 더 자유로워진다.
    @Query(value = "select b from Book b " +
            "where name = :name and createdAt >= :createdAt and updatedAt >= :updatedAt and category is null") // book을 b라고 하고 b를 가져온다~
    List<Book> findByNameRecently2(
            @Param("name") String name,
            @Param("createdAt") LocalDateTime createdAt,
            @Param("updatedAt") LocalDateTime updatedAt);


//    @Query(value = "select b.name as name, b.category as category from Book b")
////    List<Tuple> findBookNameAndCategory();
//    List<BookNameAndCategory> findBookNameAndCategory(); // interface를 통해 필요한 값만 가져오기

    // class를 통해 필요한 값만 가져오려면 해당 class의 위치를 정확하게 대입해줘야한다.
    @Query(value = "select new com.example.jpa_study.repository.dto.BookNameAndCategory(b.name, b.category) from Book b")
    List<BookNameAndCategory> findBookNameAndCategory(); // class를 통해 필요한 값만 가져오기




}
