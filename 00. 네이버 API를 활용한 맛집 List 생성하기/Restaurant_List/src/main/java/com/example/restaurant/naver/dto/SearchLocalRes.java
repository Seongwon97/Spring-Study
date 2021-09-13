package com.example.restaurant.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchLocalRes {

    // https://developers.naver.com/docs/serviceapi/search/local/local.md#%EC%A7%80%EC%97%AD
    // 출력결과 관리
    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<SearchLocalItem> items; // items에 item이 들어있어 List로 관리




    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchLocalItem {
        private String title;
        private String link;
        private String description;
        private String category;
        private String telephone;
        private String address;
        private String roadAddress;
        private int mapx;
        private int mapy;
    }
}
