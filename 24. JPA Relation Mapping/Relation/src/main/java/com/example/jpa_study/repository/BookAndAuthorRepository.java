package com.example.jpa_study.repository;

import com.example.jpa_study.entity.BookAndAuthor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookAndAuthorRepository extends JpaRepository<BookAndAuthor, Long> {
}
