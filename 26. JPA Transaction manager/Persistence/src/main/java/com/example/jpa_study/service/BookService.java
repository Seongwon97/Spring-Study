package com.example.jpa_study.service;

import com.example.jpa_study.entity.Author;
import com.example.jpa_study.entity.Book;
import com.example.jpa_study.repository.AuthorRepository;
import com.example.jpa_study.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class BookService {
//    @Autowired
//    private BookRepository bookRepository;
//    @Autowired
//    private AuthorRepository authorRepository;
//    위의 코드들은 @RequiredArgsConstructor를 붙이고 아래와 같이 final로 Repository변수를 만들어주면 자동으로 생성된다.
//    생성되는 이유는 final이라 반드시 constructor가 생성되어야하는데 @RequiredArgsConstructor가 자동으로 생성시켜주기 때문이다.
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final EntityManager entityManager;
    private final AuthorService authorService;

    public void put() {
        this.putBookAndAuthor();
    }
    //  Transactioinal이 붙어있는 A method를 B method에서 호출하였을 때 A에서 unchecked exception이 발생하여도 rollback되지 않는다.
    //  Spring Container는 Bean으로 진입할 때 달려있는 어노테이션들을 처리하도록 되어 있다.
    //  B라는 메서드에 진입하는 순간 container는 Bean내부로 들어왔기에 B내부에서 다른 메서드인 A를 호출 할 시 A의 메서드에 있는 어노테이션들은 무시가 된다.

    //@Transactional(rollbackFor = Exception.class)
    // Transactional이 붙은 method들은 하나의 Transaction으로 관리되어
    // DB의 반영을 method가 끝날 때 한번에 한다.

    @Transactional(propagation = Propagation.REQUIRED)
    // Propagation.REQUIRED -> 트렌젝션이 필요한데 만약에 있으면 재활용을 하고 없으면 새로 만든다. (default값) (jpa의 save메서드가 REQUIRED로 만들어져있다.)
    public void putBookAndAuthor(){
        Book book = new Book();
        book.setName("JPA 시작하기");
        bookRepository.save(book);

        try {
            authorService.putAuthor();
        } catch (RuntimeException e) {

        }


//        Author author = new Author();
//        author.setName("Seongwon");
//        authorRepository.save(author);
// Transactional내에서 개발자가 강제로 Exception을 실행시키는 코드를 넣을 때는 Checked exception인지 unchecked exception인지 확인을 해야한다.
// Unchecked exception이 발생하면 트렌젝션 내용이 roll back이 되고 Checked exception은 트랜젝션 내에서 발생하여도 roll back되지 않고 반영이 된다.
//        throw new RuntimeException("오류가 나서 DB commit이 발생하지 않았습니다.");
    }


    // READ_UNCOMMITTED는 데이터 정확성의 문제를 갖고있고, REPEATABLE_READ는 성능상의 문제가 있어 보통 READ_COMMITTED와 REPEATABLE_READ를 많이 이용한다.
    //@Transactional(isolation = Isolation.READ_UNCOMMITTED) // 아직 commit되지 않은 데이터가 읽혀지는 Dirty read이다. (데이터 정확성에 문제가 있다.)
    //@Transactional(isolation = Isolation.READ_COMMITTED) // READ_UNCOMMITED의 dirty read의 문제를 개선한 것이 READ_COMMITED이다.
                                                        // commit된 데이터만 가져오겠다는 뜻이다.
    //@Transactional(isolation = Isolation.REPEATABLE_READ)
    @Transactional(isolation = Isolation.SERIALIZABLE) // Commit이 일어나지 않은 Transaction이 존재하게 되면 Lock을 걸어 waiting을 하고
                                                        // commit이 실행되어야지만 추가적인 logic을 진행한다.
                                                        // waiting을 하여 성능상에 문제가 발생한다.
    public void get(Long id) {
        System.out.println(">>>1 "+ bookRepository.findById(id));
        System.out.println(">>>2 "+ bookRepository.findAll());

        entityManager.clear();
        // 앞에서 값 조회를 하고 다음에 또 해당 값을 읽으려는데 중간에 값이 변경된 경우가 발생하기도 한다.
        // 이러한 현상을 unrepeatable read상태라고 한다.
        // -> 조작은 하지 않았지만 transaction내에서 값이 변경될 수 있는 현상


        // unrepeatable read상태를 예방할 수 있는게 REPEATABLE_READ이다.
        // 중간에 값이 변경되어도 자신의 Transaction이 시작할 때 읽어온 값을 별도로 저장하고
        // 해당 transaction이 끝날 때 까지는 처음에 읽어온 스냅샷 정보를 return해준다.

        System.out.println(">>>3 "+ bookRepository.findById(id));
        System.out.println(">>>4 "+ bookRepository.findAll());

        bookRepository.update();
        // transaction내에서 한개의 record를 확인하고 한개의 record에 대해서 업데이트를 하려고 예상했는데
        // 실제로는 2개의 record에 대해서 업데이트 처리를 하게 되었다,
        // -> 이렇게 경우에 따라 데이터가 안보이는데 처리가 되기도 하는 상황을 Phantom read라고 한다.
        // -> SERIALIZABLE을 통해 해결 가능

        entityManager.clear();

//        Book book = bookRepository.findById(id).get();
//        book.setName("Test");
//        bookRepository.save(book);
    }



}
