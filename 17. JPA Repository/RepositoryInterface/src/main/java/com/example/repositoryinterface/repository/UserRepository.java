package com.example.repositoryinterface.repository;

import com.example.repositoryinterface.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
