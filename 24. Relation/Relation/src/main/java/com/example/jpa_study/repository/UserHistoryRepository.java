package com.example.jpa_study.repository;

import com.example.jpa_study.entity.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
    List<UserHistory> findByUserId(Long UserId); // UserId를 찾아오는 Query

}
