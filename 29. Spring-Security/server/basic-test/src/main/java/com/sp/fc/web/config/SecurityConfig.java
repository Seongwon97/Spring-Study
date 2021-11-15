package com.sp.fc.web.config;


import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// 2개 이상의 filter chain을 구성하고 싶다면 sequrityConfig를 여러개 만들면 된다. 여러개의 sequrityConfig은 순서가 중요하여 class위에 @Order annotation을 붙여줘야한다.
@Order(1) // order가 낮은 것부터 먼저 설정을 한다.
@EnableWebSecurity(debug = true) // request가 올 떄마다 어떤 filter를 사용하고 있는지 출력을 해준다.
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
//        http.antMatcher("/**"); // 어떤 request에 대해서 해당 filter가 동작할지에 대해서는 HTTP자체의 antMatcher를 통해 설정한다.
        // 모든 request에 대해 동작하려면 위와 같이 ("/**"), /api~~에 대해서 적용을 하고 싶다면 ("/api/**") 와 같이 해줘야한다.
        // 2개 이상의 filter chain을 구성하고 싶다면 sequrityConfig를 여러개 만들면 된다. 여러개의 sequrityConfig은 순서가 중요하여 class위에 @Order annotation을 붙여줘야한다.
        http.authorizeRequests((requests) ->
                requests.antMatchers("/").permitAll()
                        // 특정 page는 누구나 접근하도록 하려면 antMatcher를 사용하여 permitAll을 해주면 된다.
                        .anyRequest().authenticated()
                // 기본적으로 HttpSecurity는 모든 request에 대해서 인증을 받도록 설정되어 있다.
        );
        http.formLogin(login->
                login.defaultSuccessUrl("/", false));
        // True로 설정 시 로그인이 성공하면 해당 페이지로 이동한다.
        // 하지만 다른 페이지에서 login 페이지로 이동하였을 때 이전 페이지로 이동해야 하는 경우가 발생하여 false로 설정정
       http.httpBasic();
    }
}
