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

//    @Test
//    void crudTest() {
//        BookReviewInfo bookReviewInfo = new BookReviewInfo();
//        bookReviewInfo.setBookId(1L);
//        bookReviewInfo.setAverageReviewScore(4.5f);
//        bookReviewInfo.setReviewCount(2);
//
//        bookReviewInfoRepository.save(bookReviewInfo);
//
//        System.out.println(">>> " + bookReviewInfoRepository.findAll());
//    }


    // JPA기능 말고 그냥 리뷰에서 Book ID값을 찾아 검색한 1:1예제
    //  BookReviewInfo에서 private Book book;대신 private Long bookId; 사용
//    @Test
//    void crudTest2() {
//        Book book = new Book();
//        book.setName("모든 순간이 너였다");
//        book.setAuthorId(1L);
//        book.setPublisherId(1L);
//
//        bookRepository.save(book);
//
//        BookReviewInfo bookReviewInfo = new BookReviewInfo();
//        bookReviewInfo.setBookId(1L);
//        bookReviewInfo.setAverageReviewScore(4.5f);
//        bookReviewInfo.setReviewCount(2);
//
//        bookReviewInfoRepository.save(bookReviewInfo);
//
//        System.out.println(">>> " +bookReviewInfoRepository.findAll());
//
//
//        Book result = bookRepository.findById(
//                bookReviewInfoRepository.findById(1L) // bookReviewInfoRepository에서 ID가 1인 리뷰를 찾고
//                        .orElseThrow(RuntimeException::new).
//                        getBookId() // 해당 리뷰의 BookId를 찾아서 검색
//        ).orElseThrow(RuntimeException::new);
//
//        System.out.println(">>> "+result);
//    }

    private Book givenBook() {
        Book book = new Book();
        book.setName("모든 순간이 너였다");
        book.setAuthorId(1L);
        book.setPublisherId(1L);

        bookRepository.save(book);


        return bookRepository.save(book);
    }

    private void givenBookInfo(){
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(givenBook());
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);

        System.out.println(">>> " +bookReviewInfoRepository.findAll());

    }

    // CrudTest2와는 다르게 JPA에서 제공하는 @OneToOne과 같은 annotation을 사용하여
    // Id가 아닌 entity를 사용하여 탐색을 하였다.
    @Test
    void crudTest3() {
        givenBook();
        givenBookInfo();
        Book result = bookReviewInfoRepository.findById(1L)
                        .orElseThrow(RuntimeException::new).
                        getBook();
        System.out.println(">>> "+result);

        BookReviewInfo result2 = bookRepository
                .findById(1L)
                .orElseThrow(RuntimeException::new)
                .getBookReviewInfo();

        System.out.println(">>> "+result2);
    }
}