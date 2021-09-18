package com.example.jpa_study.repository;

import com.example.jpa_study.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    void bookTest() {
        Book book = new Book();
        book.setName("모든 순간이 너였다.");
        book.setAuthorId(1L);
        book.setPublisherId(1L);
        bookRepository.save(book);

        System.out.println(bookRepository.findAll());
    }
}
