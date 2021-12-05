package inflearn.inflearnspringstart;

import inflearn.inflearnspringstart.repository.JdbcMemberRepository;
import inflearn.inflearnspringstart.repository.JdbcTemplateMemberRepository;
import inflearn.inflearnspringstart.repository.MemberRepository;
import inflearn.inflearnspringstart.repository.MemoryMemberRepository;
import inflearn.inflearnspringstart.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
