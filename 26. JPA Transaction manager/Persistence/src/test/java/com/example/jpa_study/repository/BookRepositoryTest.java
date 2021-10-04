package com.example.jpa_study.repository;

import com.example.jpa_study.entity.Book;
import com.example.jpa_study.entity.Publisher;
import com.example.jpa_study.entity.Review;
import com.example.jpa_study.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;


    @Test
    void bookTest() {
        Book book = new Book();
        book.setName("모든 순간이 너였다.");
        book.setAuthorId(1L);
//        book.setPublisherId(1L);
        bookRepository.save(book);

        System.out.println(bookRepository.findAll());
    }

    @Test
    @Transactional
    void bookRelationTest() {
        givenBookAndReview();

        User user = userRepository.findByEmail("sophia@gmail.com");

        System.out.println("Review : "+user.getReviews());
        System.out.println("Book : "+user.getReviews().get(0).getBook());
        System.out.println("Publisher : "+user.getReviews().get(0).getBook().getPublisher());

    }

    private void givenBookAndReview() {
        givenReview(givenUser(), givenBook(givenPublisher()));
    }

    private void givenReview(User user, Book book) {
        Review review = new Review();
        review.setTitle("최고의 책");
        review.setContent("올해 최고의 책입니다.");
        review.setScore(4.6f);
        review.setUser(user);
        review.setBook(book);

        reviewRepository.save(review);
    }

    private Book givenBook(Publisher publisher) {
        Book book = new Book();
        book.setName("모든 순간이 너였다.");
        book.setPublisher(publisher);

        return bookRepository.save(book);
    }

    private User givenUser(){
        return userRepository.findByEmail("sophia@gmail.com");
    }

    private Publisher givenPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("성원 출판사");
        return publisherRepository.save(publisher);
    }
}
