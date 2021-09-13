package com.example.reataurant_list.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// MemoryDbEntity를 상속받은 Generic타입이 필요!
public abstract class MemoryDbRepositoryAbstract<T extends MemoryDbEntity> implements MemoryDbRepositoryIfs<T> {

    private final List<T> db = new ArrayList<>();
    private int index = 0;

    @Override
    public Optional<T> findById(int index) {
        db.stream().filter(it -> it.getIndex() == index).findFirst();
        return Optional.empty();
    }

    @Override
    public T save(T entity) {
        var optionalEntity = db.stream().filter(it -> it.getIndex() == entity.getIndex()).findFirst();
        if(optionalEntity.isEmpty()) {
            // db에 데이터가 없는 경우
            index++;
            entity.setIndex(index);
            db.add(entity);
            return entity;
        }
        else {
            // db에 이미 데이터가 있는 경우
            // 기존 데이터를 읽어와 preIndex에 저장하고 새로운 entity를 추가하고 preIndex는 삭제한다.
            var preIndex = optionalEntity.get().getIndex();
            entity.setIndex(preIndex);

            deleteById(preIndex);
            db.add(entity);
            return entity;
        }

    }

    @Override
    public void deleteById(int index) {
        var optionalEntity = db.stream().filter(it -> it.getIndex() == index).findFirst();
        if(optionalEntity.isPresent()){ // 데이터가 이미 있다면 해당 데이터를 삭제
            db.remove(optionalEntity.get());
        }

    }

    @Override
    public List<T> listAll() {
        return db;
    }
}
