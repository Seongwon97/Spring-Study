package com.sp.fc.web.config;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true) // 사전에 prePost로 권한체크를 하겠다는 설정!!
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // application.yml파일에는 한명의 유저의 정보만 담아서 test할 수 있다.
    // 여러 유저에 대해서 테스트를 하고 싶으면 아래와 같이 configure의 AuthenticationManagerBuilder를 override 해줘야한다.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication() // inMemoryAuthentication()를 추가하게되면 application.yml의 유저는 동작하지 않게 된다.
                .withUser(User.builder()
                        .username("user2")
                        .password(passwordEncoder().encode("2222"))
                        .roles("USER")
                ).withUser(User.builder()
                        .username("admin")
                        .password(passwordEncoder().encode("3333"))
                        .roles("ADMIN")
                );
    }

    // 사용자의 password는 endcoder로 encoding후 사용해줘야한다.
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Spring security는 기본적으로 모든 page를 막고 시작한다.
    // 홈페이지 같이 누구나 접근할 수 있도록 하고싶으면 configure의 HttpSecurity를 ovrride해줘야한다.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests((requests) ->
                requests.antMatchers("/").permitAll()
                        // 특정 page는 누구나 접근하도록 하려면 antMatcher를 사용하여 permitAll을 해주면 된다.
                        .anyRequest().authenticated()
                // 기본적으로 HttpSecurity는 모든 request에 대해서 인증을 받도록 설정되어 있다.
        );
        http.formLogin();
        http.httpBasic();
    }
}
