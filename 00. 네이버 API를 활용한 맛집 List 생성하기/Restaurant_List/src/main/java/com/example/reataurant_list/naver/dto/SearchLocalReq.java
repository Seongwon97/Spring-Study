package com.example.reataurant_list.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchLocalReq {

    // https://developers.naver.com/docs/serviceapi/search/local/local.md#%EC%A7%80%EC%97%AD
    // 위으 Api사용 가이드에서 3번 요청변수의 기본값들 설정
    private String query = "";
    private int display = 1;
    private int start = 1;
    private String sort = "random";

    public MultiValueMap<String, String> toMultiValueMap() {
        var map = new LinkedMultiValueMap<String, String>();

        map.add("query", query);
        map.add("display", String.valueOf(display));
        map.add("start", String.valueOf(start));
        map.add("sort", sort);
        return map;
    }
}
