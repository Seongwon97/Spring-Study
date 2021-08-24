package com.example.springioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/*component를 붙여 Bean에 넣었을 때
    public Encoder(IEncoder iEncoder){

        this.iEncoder = iEncoder;
    }
    다음과 같은 코드에서 연결되는 class가 하나이면
    오류가 발생하지 않고 Spring에서 알아서 연결해준다
    하지만 현재 iEncoder는 matching되는 것이 Base64Encoder와 UrlEncoder두개가 연결되어 있기에
    @Qualifier를 통해 연결해줄 class를 지정해줘야한다.
*/
@Component
public class Encoder implements IEncoder{

    private IEncoder iEncoder;

    public Encoder(@Qualifier("urlEncoder") IEncoder iEncoder){
        // @Qualifier를 사용해 urlEncoder를 할당
        this.iEncoder = iEncoder;
    }

    public void setiEncoder(IEncoder iEncoder){
        this.iEncoder = iEncoder;
    }

    public String encode(String message){
        return iEncoder.encode(message);
    }
}
