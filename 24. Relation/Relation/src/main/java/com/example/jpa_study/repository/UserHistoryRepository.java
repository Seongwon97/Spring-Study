package com.example.jpa_study.repository;

import com.example.jpa_study.entity.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {


}
