package com.example.jpa_study.domain;

// enum은 java에서 사용하는 상수 객체이다.
// DB에서는 enum의 값들을 기본적으로 데이터를 0,1,~순으로 값을 저장한다.
// 아래 Gender를 예시로 들면 DB에 값을 저장하면 MALE은 0, FEMALE은 1로 DB에 저장된다.
// User객체의 gender를 변수에 @Enumerated를 확인해보면 default값이 ORDINAL인 것을 확인할 수 있다.(STRING이 아니다.)
// String으로 바꾸려면 @Enumerated(value = EnumType.STRING)으로 변경해줘야한다.
public enum Gender {
    MALE,
    FEMALE
}
