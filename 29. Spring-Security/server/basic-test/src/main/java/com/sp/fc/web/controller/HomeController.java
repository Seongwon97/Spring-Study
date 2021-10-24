package com.sp.fc.web.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String index() {
        return "홈페이지";
    }


    @RequestMapping("/auth")
    public Authentication auth(){ // 사용자가 어떤 권한으로 접근했는지 확인가능
        return SecurityContextHolder.getContext()
                .getAuthentication();
    }

    // 개인정보에 관련된 resource 시뮬레이션
    @PreAuthorize("hasAnyAuthority('ROLE_USER')") // USER권한이 있어야만 접근 가능
    @RequestMapping("/user")
    public SecurityMessage user() { // user가 접근하는 페이지
        return SecurityMessage.builder()
                .auth(SecurityContextHolder.getContext().getAuthentication())
                .message("User 정보")
                .build();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')") // ADMIN의 권한이 있어야지만 접근 가능
    @RequestMapping("/admin")
    public SecurityMessage admin() { // admin이 접근하는 페이지
        return SecurityMessage.builder()
                .auth(SecurityContextHolder.getContext().getAuthentication())
                .message("관리자 정보")
                .build();
    }
}
