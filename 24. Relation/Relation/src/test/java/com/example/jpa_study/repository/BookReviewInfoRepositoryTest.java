package com.example.jpa_study.repository;

import com.example.jpa_study.entity.Book;
import com.example.jpa_study.entity.BookReviewInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class BookReviewInfoRepositoryTest {
    @Autowired
    private BookReviewInfoRepository bookReviewInfoRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void crudTest() {
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBookId(1L);
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);

        System.out.println(">>> " + bookReviewInfoRepository.findAll());
    }

    @Test
    void crudTest2() {
        Book book = new Book();
        book.setName("모든 순간이 너였다");
        book.setAuthorId(1L);
        book.setPublisherId(1L);

        bookRepository.save(book);

        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBookId(1L);
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);

        System.out.println(">>> " +bookReviewInfoRepository.findAll());


        Book result = bookRepository.findById(
                bookReviewInfoRepository.findById(1L) // bookReviewInfoRepository에서 ID가 1인 리뷰를 찾고
                        .orElseThrow(RuntimeException::new).
                        getBookId() // 해당 리뷰의 BookId를 찾아서 검색
        ).orElseThrow(RuntimeException::new);

        System.out.println(">>> "+result);
    }
}