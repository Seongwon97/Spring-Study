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

        // 둘 사이의 연관관계 생성 Book.java에서 publisher의 reloation annotation에 CascadeType.PERSIST이 필요하다.
        book.setPublisher(publisher);
        bookRepository.save(book);

        //publisher.getBook().add(book);
//        publisher.addBook(book);
//        publisherRepository.save(publisher);

        System.out.println("books : "+bookRepository.findAll());
        System.out.println("publisher : "+publisherRepository.findAll());


        // 값 업데이트를 위해서는 Book.java에서 publisher의 reloation annotation에 CascadeType.MERGE가 필요하다.
        Book book1 = bookRepository.findById(1L).get();
        book1.getPublisher().setName("모든 순간");

        bookRepository.save(book1);

        System.out.println("books : "+bookRepository.findAll());
        System.out.println("publisher : "+publisherRepository.findAll());


        //Delete를 하기 위해서는 Book.java에서 publisher의 reloation annotation에 CascadeType.REMOVE가 필요하다.
        Book book2 = bookRepository.findById(2L).get();
        bookRepository.delete(book2);
//        bookRepository.deleteById(1L);

        System.out.println("books : "+bookRepository.findAll());
        System.out.println("publisher : "+publisherRepository.findAll());

    }

    @Test
    // reloation annotation의 고아제거속성(orphanRemoval)은 연관관계가 없는 entity를 제거하는 속성이다.
    // cascade의 CascadeType.REMOVE은 삭제되는 객체와 연결된 객체들도 모두 삭제가 되지만 연관관계가 끊어질 때는 연결되어 있던 객체들은 삭제되지 않는다.
    // 연관관계를 끊었을 때 연결되어 있었던 객체들을 삭제하려면 orphanRemoval속석을 true로 설정해주면 된다.
    // 연관관계는 내부에서 set메서드를 통해 연결되는 값들(ex. book엔티티에서는 publisher)을 null로 변경하며 끊어낸다.
    void bookRemoveCascadeTest() {
        System.out.println("books : "+bookRepository.findAll());
        System.out.println("publishers : "+publisherRepository.findAll());

        bookRepository.findAll().forEach(book -> System.out.println(book.getPublisher()));
    }

    @Test
    void softDelete() {
        bookRepository.findAll().forEach(System.out::println);
//        bookRepository.findAllByDeletedFalse().forEach(System.out::println);
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
