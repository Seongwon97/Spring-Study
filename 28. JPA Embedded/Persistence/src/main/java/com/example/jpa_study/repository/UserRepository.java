package com.example.jpa_study.repository;

import com.example.jpa_study.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
    User findByEmail(String email);

    @Query(value = "select * from user limit 1;", nativeQuery = true)
    Map<String, Object> findRawRecord();

    @Query(value = "select * from user", nativeQuery = true)
    List<Map<String, Object>> findAllRawRecord();

}
