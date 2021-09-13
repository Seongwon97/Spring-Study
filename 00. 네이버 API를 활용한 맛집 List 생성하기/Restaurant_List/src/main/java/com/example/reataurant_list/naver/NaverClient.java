package com.example.reataurant_list.naver;

import com.example.reataurant_list.naver.dto.SearchLocalReq;
import com.example.reataurant_list.naver.dto.SearchLocalRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class NaverClient {

    // application.yaml에서 설정한 값들을 사용하는 방법
    @Value("${naver.client.id}") // lombok이 아닌 Spring annotation호출
    private String naverClientId;

    @Value("${naver.client.secret}")
    private String naverClientSecret;

    @Value("${naver.url.search.local}")
    private String naverLocalSearchUrl;

    @Value("${naver.url.search.image}")
    private String naverImageSearchUrl;


    public SearchLocalRes searchLocal(SearchLocalReq searchLocalReq) {
        var uri = UriComponentsBuilder.fromUriString(naverLocalSearchUrl)
                .queryParams(searchLocalReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        // https://developers.naver.com/docs/serviceapi/search/local/local.md#%EC%A7%80%EC%97%AD
        // 6번 예시의 호출에 header에 필요한 필수값 추가
        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(headers);
        var responseType = new ParameterizedTypeReference<SearchLocalRes>(){};



        var responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );

        return responseEntity.getBody();

    }

    public void searchImage(){

    }

}
