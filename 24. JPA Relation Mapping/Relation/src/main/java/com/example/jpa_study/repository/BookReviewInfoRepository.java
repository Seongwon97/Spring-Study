package com.example.jpa_study.repository;

import com.example.jpa_study.entity.BookReviewInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReviewInfoRepository extends JpaRepository<BookReviewInfo, Long> {

}
