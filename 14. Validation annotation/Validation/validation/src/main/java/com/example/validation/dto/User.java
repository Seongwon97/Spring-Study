package com.example.validation.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class User {

    @NotBlank // 공백 허용안함
    private String name;

    @Max(value = 90)
    private int age;

    @Email // -> email형식이 아닌 데이터가 들어오면 오류 발생시킨다.
    private String email;

    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "핸드폰 번호의 약식과 맞지 않습니다. xxx-xxxx-xxxx")
    // 받아들이는 문자의 형태를 직접 제한하는 방법 (정규식을 사용)
    // message에는 error가 발생했을 때 출력할 메세지를 적는다.
    private String phoneNumber;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
