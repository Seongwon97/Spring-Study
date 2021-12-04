package inflearn.inflearnspringstart;

import inflearn.inflearnspringstart.repository.MemberRepository;
import inflearn.inflearnspringstart.repository.MemoryMemberRepository;
import inflearn.inflearnspringstart.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
