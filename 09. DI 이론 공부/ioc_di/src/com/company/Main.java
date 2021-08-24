package com.company;

import java.net.URLEncoder;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String url = "www.naver.com/books/it?page=10?size=20?name=spring";


        // Base 64 encoding
        IEncoder baseEncoder = new Base64Encoder();
        String baseResult = baseEncoder.encode(url);
        System.out.println(baseResult);


        // URL encoder
        IEncoder urlEncoder = new UrlEncoder();
        String urlResult = urlEncoder.encode(url);
        System.out.println(urlResult);

        // 이것을 DI를 적용
        // Encoder입장에서는 urlEncoder를 외부에서 주입을 받았기 떄문에 DI라고 한다.
        // 넣은 객체에 따라 다른 결과를 가져오며 Spring에은 이러한 식으로 운영된다.
        Encoder encoder = new Encoder(new UrlEncoder());
        String result = encoder.encode(url);
        System.out.println(result);
    }
}
