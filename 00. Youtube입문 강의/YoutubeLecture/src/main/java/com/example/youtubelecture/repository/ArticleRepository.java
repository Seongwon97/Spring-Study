package com.example.youtubelecture.repository;


import com.example.youtubelecture.entity.Article;
import org.springframework.data.repository.CrudRepository;

// JPA에서 제공하는 CrudRepository를 상속 (CRUD를 추가 코드 없이 사용할 수 있다.)
// CrudRepository<관리대상 entity, 관리대상 entity에서 key값의 데이터 타입>
public interface ArticleRepository extends CrudRepository<Article, Long> {

}
