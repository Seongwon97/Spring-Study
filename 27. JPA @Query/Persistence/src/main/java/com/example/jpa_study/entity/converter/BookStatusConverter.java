package com.example.jpa_study.entity.converter;

import com.example.jpa_study.repository.dto.BookStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

// AttributeConverter의 <>의 첫번째 값은 entity attribute의 타입이고
// 두번째 값은 database column의 타입이다.

// autoApply옵션
// autoApply를 사용하지 않으면 해당 entity(여기서는 BookStatus)를 지정한 객체에 @Convert를 붙여줘야하지만
// autoApply=true를 하면 convert를 붙여주지 않아도 자동으로 적용이 된다.
// 주의할 점: StringConverter, IntegerConverter와 같은 것을 만들어서 autoApply를 적용하게 되면
// 우리가 변경하고자 했던것 이외의 모든 것들이 converter를 타게 되어서 별개의 Class를 생성해서 사용할때만 사용하는게 좋다.

@Converter(autoApply = true) // converter를 자주 사용하면 autoApply옵션을 사용하는게 좋다.
public class BookStatusConverter implements AttributeConverter<BookStatus, Integer> {
    // Connverter는 양쪽의 데이터를 서로 바꿔주도록 아래의 메서드를 재정의해줘야한다.
    // 둘 중 하나를 구현하는 경우 실제 데이터가 유실되는 문제가 발생할 수 있다.


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
