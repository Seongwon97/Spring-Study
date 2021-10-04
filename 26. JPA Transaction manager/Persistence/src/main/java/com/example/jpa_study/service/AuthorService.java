package com.example.jpa_study.service;

import com.example.jpa_study.entity.Author;
import com.example.jpa_study.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
// @Transactional 이와같이 class에 Transactional을 붙일 수도 있다.
// 이러한 경우 Class전체를 Transaction화 하겠다는게 아니고 class내부에 있는 각각의 method를 transaction화 하겠다는 것이다.
// method에 추가로 @Transactional을 붙이면 method에 붙은 걸 우선 적용한다.
public class AuthorService {
    private final AuthorRepository authorRepository;

    //@Transactional(propagation = Propagation.REQUIRED)
    // Propagation.REQUIRED -> 트렌젝션이 필요한데 만약에 있으면 재활용을 하고 없으면 새로 만든다. (default값) (jpa의 save메서드가 REQUIRED로 만들어져있다.)
    // REQUIRED는 하나의 트렌젝션을 여러 메서드에서 사용하기에 한 곳에서 Exception이 발생하면 전체가 Exception이 발생한다. (모두가 커밋되거나 모두가 롤백되던가!)

    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    // 트렌젝션이 필요하면 기존에 존재하여도 새로 만들어서 따로 관리한다.

    //@Transactional(propagation = Propagation.SUPPORTS)
    // 호출하는 쪽에서 Transaction이 있다면 해당 Transaction을 호출해서 사용한다.
    // REQUIRED와 다르게 호출하는 쪽에 Transaction이 없다면 굳이 Transcation을 생성하지 않고 그냥 처리한다.

    //@Transactional(propagation = Propagation.NOT_SUPPORTED)
    // 호출하는 쪽에서 Transaction이 있다면 해당 Transaction을 멈추고 transaction없이 해당 method(호출된)를 실행한다.

    //@Transactional(propagation = Propagation.MANDATORY)
    // 이미 생성된 Transaction이 무조건 있어야하며, 만약 없다면 오류를 발생시킨다.

    //@Transactional(propagation = Propagation.NEVER)
    // MANDATORY의 반대로 Transaction이 무조건 없어야하며, 만약 있다면 오류를 발생시킨다.

    @Transactional(propagation = Propagation.NESTED)
    // REQUIRES_NEW와 비슷하나 별도의 Transaction을 생성하는게 아니라 호출하는 곳의 transaction을 사용한다.
    // 하나의 Transaction이지만 약간 분리되어서 동작한다. 호출받는 곳에서 롤백이 일어나도 호출하는 곳은 다른 커밋은 안전하게 된다.
    // 반대로 호출하는 곳에서 롤백이 일어나면 호출하는 곳, 받는 곳 모두 롤백된다.
    public void putAuthor() {
        Author author = new Author();
        author.setName("Seongwon");

        authorRepository.save(author);


        throw new RuntimeException("오류가 나서 DB commit이 발생하지 않았습니다.");
    }
}
