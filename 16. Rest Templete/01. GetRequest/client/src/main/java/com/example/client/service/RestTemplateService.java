// 가장 기본이되는 Rest Templete
package com.example.client.service;

import com.example.client.dto.UserResponse;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {


    // http://localhost/api/server/hello 로 요청해서 response를 받아오기
    public UserResponse hello(){
        // uri 주소 생성
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090") //http://localhost에 호출
                .path("/api/server/hello")
                .queryParam("name", "steve")  // query parameter가 필요한 경우 이와 같이 사용
                .queryParam("age", 10)
                .encode()
                .build()
                .toUri();

        System.out.println(uri.toString());

        RestTemplate restTemplete = new RestTemplate();
//        String result = restTemplete.getForObject(uri, String.class); // 문자열로  Uri를 받는다.
        // getForObject는 Get Request를 uri로 보내서 결과를 두번째 parameter로 적은 Object타입으로 받아온다.

        // getForEntity로 하면 결과를 Entity로 받아 http status와 data등 여러 데이터 확인이 가능하다.
//        ResponseEntity<String> result = restTemplete.getForEntity(uri, String.class);



        // Json 타입으로 데이터를 받기 위해서는 DTO를 사용
        // 받는 데이터 타입을 DTO인 userResponse.class로 변경
        ResponseEntity<UserResponse> result = restTemplete.getForEntity(uri, UserResponse.class);
        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        return result.getBody();
    }
}
