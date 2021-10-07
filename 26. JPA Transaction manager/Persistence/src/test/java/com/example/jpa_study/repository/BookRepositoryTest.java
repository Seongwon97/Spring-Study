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

    // Cascade는 oneToOne, ManyToOne등 relaton에 있는 속성이다.
    @Test
    //@Transactional
    void BookCascadeTest() {
        // 자바 코드의 코딩 스타일대로 하면 아래와 같이 save 메서드들을
        // 계속 사용하지 않고 연관관계까지 모든 설정을 마치고 한번에 Save를 하며 DB에 올릴 것이다.
        // 하지만 그렇게 하면 연관관계를 설정하는 코드에서 DB에 해당 entity들이 들어있지 않아 오류가 발생하게된다.
        // 그래서 relation annotation의 cacade속성을 사용하여 설정해야한다.
        Book book = new Book();
        book.setName("모든 순간이 너였다");
        //bookRepository.save(book);

        Publisher publisher = new Publisher();
        publisher.setName("패스트 캠퍼스");
        //publisherRepository.save(publisher);

        // 둘 사이의 연관관계 생성
        book.setPublisher(publisher);
        bookRepository.save(book);

        //publisher.getBook().add(book);
//        publisher.addBook(book);
//        publisherRepository.save(publisher);

        System.out.println("books : "+bookRepository.findAll());
        System.out.println("publisher : "+publisherRepository.findAll());

        Book book1 = bookRepository.findById(1L).get();
        book1.getPublisher().setName("모든 순간");

        bookRepository.save(book1);

        System.out.println("books : "+bookRepository.findAll());
        System.out.println("publisher : "+publisherRepository.findAll());
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
