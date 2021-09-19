package com.example.jpa_study.repository;

import com.example.jpa_study.entity.Author;
import com.example.jpa_study.entity.Book;
import com.example.jpa_study.entity.BookAndAuthor;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookAndAuthorRepository bookAndAuthorRepository;

    @Test
    @Transactional
    void manyToManyTest() {
        Book book1 = givenBook("멀티미디어");
        Book book2 = givenBook("컴퓨터구조");
        Book book3 = givenBook("운영체제");
        Book book4 = givenBook("머신러닝");

        Author author1 = givenAuthor("Seongwon");
        Author author2 = givenAuthor("James");


        BookAndAuthor bookAndAuthor1 = givenBookAndAuthor(book1, author1);
        BookAndAuthor bookAndAuthor2 = givenBookAndAuthor(book2, author2);

        // book3은 저자가 2명이지만 oneToMany의 관계이기 때문에 2개의 recored를 생성
        BookAndAuthor bookAndAuthor3 = givenBookAndAuthor(book3, author1);
        BookAndAuthor bookAndAuthor4 = givenBookAndAuthor(book3, author2);

        BookAndAuthor bookAndAuthor5 = givenBookAndAuthor(book4, author1);
        BookAndAuthor bookAndAuthor6 = givenBookAndAuthor(book4, author2);


        book1.addBookAndAuthors(bookAndAuthor1);
        book2.addBookAndAuthors(bookAndAuthor2);
        book3.addBookAndAuthors(bookAndAuthor3, bookAndAuthor4);
        book4.addBookAndAuthors(bookAndAuthor5, bookAndAuthor6);

        author1.addBookAndAuthors(bookAndAuthor1, bookAndAuthor3, bookAndAuthor5);
        author2.addBookAndAuthors(bookAndAuthor2, bookAndAuthor4, bookAndAuthor6);

//        book1.addAuthor(author1);
//        book2.addAuthor(author2);
//        book3.addAuthor(author1, author2);
//        book4.addAuthor(author1, author2);
//
//        author1.addBook(book1, book3, book4);
//        author2.addBook(book2, book3, book4);

        bookRepository.saveAll(Lists.newArrayList(book1, book2, book3, book4));
        authorRepository.saveAll(Lists.newArrayList(author1, author2));

        bookRepository.findAll().get(2).getBookAndAuthors().forEach(o-> System.out.println(o.getAuthor()));
        authorRepository.findAll().get(0).getBookAndAuthors().forEach(o-> System.out.println(o.getBook()));

//        System.out.println("author through book : " + bookRepository.findAll().get(2).getAuthors());
//        System.out.println("books through author : " + authorRepository.findAll().get(0).getBooks());
//        일반적으로 ManyToMany(N:N)의 경우 복잡성때문에 현업에서는 잘 사용하지 않는다.
//        N:N보다는 1:1, 1:N, N:1을 많이 사용하려한다
//        Ex) User와 Product의 경우 User는 많은 Product를 구매할 수 있고 Product도 많은 User에게 팔릴 수 있어서 n:n관계이다.
//        하지만 이럴 경우 현업에서는 중간에 order table을 만들어서 user와 order, product와 order 테이블간에 n:1관계를 만들어 조회한다.
    }


    private Book givenBook(String name) {
        Book book = new Book();
        book.setName(name);

        return bookRepository.save(book);
    }

    private Author givenAuthor(String name) {
        Author author = new Author();
        author.setName(name);

        return authorRepository.save(author);
    }

    private BookAndAuthor givenBookAndAuthor(Book book, Author author) {
        BookAndAuthor bookAndAuthor = new BookAndAuthor();
        bookAndAuthor.setBook(book);
        bookAndAuthor.setAuthor(author);

        return bookAndAuthorRepository.save(bookAndAuthor);

    }
}