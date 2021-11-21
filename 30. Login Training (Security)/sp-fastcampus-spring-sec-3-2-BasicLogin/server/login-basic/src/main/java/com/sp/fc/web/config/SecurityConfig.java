package com.sp.fc.web.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true) // 정해놓은 규칙대로 하겠다~ ex) 관리자 일반 유저로 역할 나눔? -> 유저가 관리자페이지에 접근 불가~
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomAuthDetails customAuthDetails;

    public SecurityConfig(CustomAuthDetails customAuthDetails) {
        this.customAuthDetails = customAuthDetails;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser(
                        User.withDefaultPasswordEncoder()
                                .username("user1")
                                .password("1111")
                                .roles("USER")
                ).withUser(
                        User.withDefaultPasswordEncoder()
                                .username("admin")
                                .password("2222")
                                .roles("ADMIN")
                );

    }

    // 관리자가 유저 페이지에도 접근 가능하게 하는 방법
    @Bean
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER"); // admin은 user가 할 수 있는 것을 다 할 수 있다~
        return roleHierarchy;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(request->{
                    request
                            .antMatchers("/").permitAll() // root 경로를 허용
                            .anyRequest().authenticated()
                    // request에 대해서는 승인 필요 -> 이 부분만 사용시 web의 css에도 보안을 걸게 되어서 css적용이 안된다.
                    // 그래서 아래의 configure와 같이 web source에 대해서는 security가 되지 않도록 ignore를 시켜줘야한다.
                            ;
                })

                // user name space는 'formLogin()'으로 설정
                // login page를 기본적으로 설정해주지 않으면   DefaultLoginPageGeneratingFilter, DefaultLogoutPageGeneratingFilter가 기본설정으로 된다.
                .formLogin(
                        login -> login.loginPage("/login")
                                .permitAll()
                                .defaultSuccessUrl("/", false) // 로그인 성공했을 때 갈 곳이 없으면 root로 이동
                                .failureUrl("/login-error") // 로그인 실패시 해당 page로 이동
                                .authenticationDetailsSource(customAuthDetails)
                        // login에 대해서는 login page와 메인 페이지 간의 무한루프를 조심해한다. -> 반드시 permitAll()을 해줘야한다.
                        // (로그인 페이지로 가려면 로그인을 받고오려고 하고 다른 페이지를 이용하려면 로그인을 하라고 하고)
                )
                .logout(logout->logout.logoutSuccessUrl("/")) // 로그아웃시 해당 page로 이동
                .exceptionHandling(exception->exception.accessDeniedPage("/access-denied"))
                ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .requestMatchers(
                        PathRequest.toStaticResources().atCommonLocations()
                ); // PathRequesst의 commonLocation을 모두 web source로 묶어서 security가 무시되도록 한다.
    }
}
