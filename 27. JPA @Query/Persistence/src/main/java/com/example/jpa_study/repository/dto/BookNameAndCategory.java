package com.example.jpa_study.repository.dto;

// book entity의 name과 category를 별도의 class로 따로 만든다 생각해라~

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookNameAndCategory {
    private String name;
    private String category;
}

//
//public interface BookNameAndCategory {
//    String getName();
//    String getCategory();
//}