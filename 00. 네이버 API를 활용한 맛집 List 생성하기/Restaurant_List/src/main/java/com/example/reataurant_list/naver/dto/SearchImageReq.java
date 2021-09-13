package com.example.reataurant_list.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchImageReq {

    //https://developers.naver.com/docs/serviceapi/search/image/image.md#%EC%9D%B4%EB%AF%B8%EC%A7%80
    // 위으 Api사용 가이드에서 3번 요청변수의 기본값들 설정
    private String query = "";
    private int display = 1;
    private int start = 1;
    private String sort = "sim";
    private String filter = "all";

    public MultiValueMap<String, String> toMultiValueMap() {
        var map = new LinkedMultiValueMap<String, String>();

        map.add("query", query);
        map.add("display", String.valueOf(display));
        map.add("start", String.valueOf(start));
        map.add("sort", sort);
        map.add("filter", filter);
        return map;
    }
}
