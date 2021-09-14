package com.example.lombok.controller;

import lombok.*;

import java.time.LocalDateTime;


@EqualsAndHashCode(of = {"name", "email"})
public class User {
    private String name;
    private String email;
    private LocalDateTime createdAt; // 생성된 시간
    private LocalDateTime updatedAt; // 업데이트된 시간
}
