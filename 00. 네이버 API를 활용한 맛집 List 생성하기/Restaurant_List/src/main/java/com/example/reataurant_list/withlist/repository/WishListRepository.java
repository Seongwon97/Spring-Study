package com.example.reataurant_list.withlist.repository;

import com.example.reataurant_list.db.MemoryDbRepositoryAbstract;
import com.example.reataurant_list.withlist.entity.WishListEntity;
import org.springframework.stereotype.Repository;

@Repository  // repository로 동작하는 것을 알리기 위한 것 (Repository database를 저장하는 곳이다~)
public class WishListRepository extends MemoryDbRepositoryAbstract<WishListEntity> {
    // <>에 <WishListEntity>를 넣었기 때문에 추상클래스인 MemoryDbRepositoryAbstract에서 T는 모두 WishListEntity로 동작할 것이다.

}
