package com.example.jpa_study.service;

import com.example.jpa_study.entity.Author;
import com.example.jpa_study.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class AuthorService {
    private final AuthorRepository authorRepository;


    @Transactional(propagation = Propagation.NESTED)
    public void putAuthor() {
        Author author = new Author();
        author.setName("Seongwon");

        authorRepository.save(author);


        throw new RuntimeException("오류가 나서 DB commit이 발생하지 않았습니다.");
    }
}
