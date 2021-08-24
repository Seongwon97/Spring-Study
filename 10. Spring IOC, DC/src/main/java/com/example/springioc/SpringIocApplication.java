package com.example.springioc;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URLEncoder;

// Spring Coding을 할 때는 new를 통해 개발자가 직접 객체를 생성하지 않는다
// 우리는 Spring context를 통해 가져와야한다.


// @SpringBootApplication은 아래의 동작이 Spring으로 동작하게 해준다.
// 다른 Class들에 @Component를 붙이면 Spring Bean에 등록이 되는데
// 이렇게 한다면 Spring이 실행이 될 때 @Component이 붙은 Class를 찾아서
// 직접 객체를 Singleton형태로 만들어서 Spring container에서 관리를 한다.

// Bean은 Spring container에서 관리되는 객체들을 의미한다.

// container에 있는 객체는 Spring application context를 통해 객체를 가져와야한다.
@SpringBootApplication
public class SpringIocApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringIocApplication.class, args);
        // container에 있는 객체는 Spring application context를 통해 객체를 가져와야한다.
        // ApplicationContextProvider는 새로운 class를 만들며 생성했다.
        ApplicationContext context = ApplicationContextProvider.getContext();

        // Bean은 이름 또는 클래스 type을 통해 찾을 수 있다.
        //Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);
        //UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);

        // Encoder encoder = context.getBean(Encoder.class); // 이 코드는 Encoder class로 등록된 bean이 하나인경우 사용
        Encoder encoder = context.getBean("baseEncoder2", Encoder.class); // Encoder로 등록된 bean이 여러개인 경우는 이름으로 불러서 사용
        String url = "www.naver.com/books/it?page=10?size=20?name=spring";

        String result = encoder.encode(url);
        System.out.println(result);


    }

}

// Encoder.java파일의 21번째 줄에 있는 @Qualifier를 사용하면 하나의 encoder만 지정하여 사용할 수 있게 된다.
// 여러개의 encoder를 사용하는 방법에 대해 알아보고자 한다.

// 직접 bean에 등록하는 방법

@Configuration // 한개의 class에서 여러개의 Bean을 등록하겠다는 뜻
class AppConfig {

    //개발자가 사용을 해도 main code에서 new를 사용하는게 아니라 미리 @Bean을 통해 등록을 시키고 사용한다.
    @Bean("baseEncoder2")
    public Encoder encoder(Base64Encoder base64Encoder){
        return new Encoder(base64Encoder);
    }

    @Bean("urlEncoder2")
    public Encoder encoder(UrlEncoder urlEncoder){
        return new Encoder(urlEncoder);
    }
}