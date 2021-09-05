package com.example.client.dto;
/* 다음과 같은 형식의 Json object생성
header의 response_code는 항상 같지만
body의 내용은 항상 다른 json형태
{
    "header": {
        "response_code": ""
    },
    "body": {
        "book" : "spring boot",
        "page" : 1234
    }
}
*/

public class Req<T> {
    // request의 body를 generic type으로 설정
    private Header header;

    private T resBody;

    public static class Header{
        private String responseCode;

        public String getResponseCode() {
            return responseCode;
        }

        public void setResponseCode(String responseCode) {
            this.responseCode = responseCode;
        }

        @Override
        public String toString() {
            return "Header{" +
                    "responseCode='" + responseCode + '\'' +
                    '}';
        }
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public T getResBody() {
        return resBody;
    }

    public void setResBody(T resBody) {
        this.resBody = resBody;
    }

    @Override
    public String toString() {
        return "Req{" +
                "header=" + header +
                ", body=" + resBody +
                '}';
    }
}
