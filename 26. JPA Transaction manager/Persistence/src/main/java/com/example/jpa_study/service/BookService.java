package com.example.jpa_study.service;

import com.example.jpa_study.entity.Author;
import com.example.jpa_study.entity.Book;
import com.example.jpa_study.repository.AuthorRepository;
import com.example.jpa_study.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void put() {
        this.putBookAndAuthor();
    }
    //  Transactioinal이 붙어있는 A method를 B method에서 호출하였을 때 A에서 unchecked exception이 발생하여도 rollback되지 않는다.
    //  Spring Container는 Bean으로 진입할 때 달려있는 어노테이션들을 처리하도록 되어 있다.
    //  B라는 메서드에 진입하는 순간 container는 Bean내부로 들어왔기에 B내부에서 다른 메서드인 A를 호출 할 시 A의 메서드에 있는 어노테이션들은 무시가 된다.

    @Transactional(rollbackFor = Exception.class)
    // Transactional이 붙은 method들은 하나의 Transaction으로 관리되어
    // DB의 반영을 method가 끝날 때 한번에 한다.
    public void putBookAndAuthor(){
         Book book = new Book();
        book.setName("JPA 시작하기");
        bookRepository.save(book);

        Author author = new Author();
        author.setName("Seongwon");
        authorRepository.save(author);
// Transactional내에서 개발자가 강제로 Exception을 실행시키는 코드를 넣을 때는 Checked exception인지 unchecked exception인지 확인을 해야한다.
// Unchecked exception이 발생하면 트렌젝션 내용이 roll back이 되고 Checked exception은 트랜젝션 내에서 발생하여도 roll back되지 않고 반영이 된다.
        throw new RuntimeException("오류가 나서 DB commit이 발생하지 않았습니다.");
    }
}
