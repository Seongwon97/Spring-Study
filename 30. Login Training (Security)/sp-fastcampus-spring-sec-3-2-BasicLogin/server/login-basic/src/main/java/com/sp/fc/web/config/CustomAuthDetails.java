package com.sp.fc.web.config;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

// AuthenticationDetailsSource를 implement하고 HttpServletRequest까지는 넣어줘야한다. 그 후에 들어갈 파라미터는 넘겨줄 객체를 넣어줘야한다.
@Component
public class CustomAuthDetails implements AuthenticationDetailsSource<HttpServletRequest, RequestInfo> {

    @Override
    public RequestInfo buildDetails(HttpServletRequest request) {
        return RequestInfo.builder()
                // proxy를 거쳐오면 값이 바뀌기에 아래와 같이 지정해줘야한다.
                .remoteIp(request.getRemoteAddr())
                .sessionId(request.getSession().getId())
                .loginTime(LocalDateTime.now())
                .build();
    }
}
