package com.example.reataurant_list.db;

import java.util.List;
import java.util.Optional;

public interface MemoryDbRepositoryIfs<T> {
    Optional<T> findById(int index); // 데이터를 찾는 method
    T save(T entity); // 데이터를 저장하는 method
    void deleteById(int index); // 데이터를 삭제하는 method
    List<T> listAll(); // 모든 데이터를 출력하는 method
}
