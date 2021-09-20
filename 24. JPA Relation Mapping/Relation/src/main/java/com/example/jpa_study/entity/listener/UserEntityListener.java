package com.example.jpa_study.entity.listener;


import com.example.jpa_study.entity.User;
import com.example.jpa_study.entity.UserHistory;
import com.example.jpa_study.repository.UserHistoryRepository;
import com.example.jpa_study.support.BeanUtils;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

// User의 정보를 생성하거나 변경할 때마다 History DB에 값들을 저장하게 하는 Listener


//@Component // Autowired를 하기 위해서는 Spring Bean으로 지정해애햐서 Component로 만들어줘야한다.
public class UserEntityListener {
//    @Autowired
//    private UserHistoryRepository userHistoryRepository;
// userHistoryRepository이 초기에 null값이라 Spring Bean을 가져오지 못하여
// @Autowired를 통해 불러오는 대신 Bean을 가져올 수 있도록 해주는 BeanUtils을 이용하여 불러온다. (25번째 줄)

    @PostPersist
    @PostUpdate
    public void prePersistAndPreUpdate(Object o) {
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);
        User user = (User)o;
        UserHistory userHistory = new UserHistory();
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());
        userHistory.setUser(user);

        userHistoryRepository.save(userHistory);
    }
}
