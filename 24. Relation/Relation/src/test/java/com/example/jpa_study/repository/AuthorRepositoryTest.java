package com.example.jpa_study.repository;

import com.example.jpa_study.entity.Author;
import com.example.jpa_study.entity.Book;
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

    @Test
    @Transactional
    void manyToManyTest() {
        Book book1 = givenBook("멀티미디어");
        Book book2 = givenBook("컴퓨터구조");
        Book book3 = givenBook("운영체제");
        Book book4 = givenBook("머신러닝");

        Author author1 = givenAuthor("Seongwon");
        Author author2 = givenAuthor("James");

        book1.addAuthor(author1);
        book2.addAuthor(author2);
        book3.addAuthor(author1, author2);
        book4.addAuthor(author1, author2);

        author1.addBook(book1, book3, book4);
        author2.addBook(book2, book3, book4);

        bookRepository.saveAll(Lists.newArrayList(book1, book2, book3, book4));
        authorRepository.saveAll(Lists.newArrayList(author1, author2));

        System.out.println("author through book : " + bookRepository.findAll().get(2).getAuthors());
        System.out.println("books through author : " + authorRepository.findAll().get(0).getBooks());
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
}