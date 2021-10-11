package com.example.jpa_study.entity.converter;

import com.example.jpa_study.repository.dto.BookStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

// AttributeConverter의 <>의 첫번째 값은 entity attribute의 타입이고
// 두번째 값은 database column의 타입이다.
@Converter
public class BookStatusConverter implements AttributeConverter<BookStatus, Integer> {

    // BookStatus객체를 받아서 DB에 저장할 때 어떻게 할 것이냐~
    @Override
    public Integer convertToDatabaseColumn(BookStatus attribute) {
        return attribute.getCode();
    }

    @Override
    public BookStatus convertToEntityAttribute(Integer dbData) {
        return dbData != null ? new BookStatus(dbData) : null;
    }
}
