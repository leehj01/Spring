package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.beans.Beans;

public class AutoAppConfigTest {

    @Test
    void baiscScan() { // autoappconfig 들어가도 아무것도 없다.- 그래도 잘 조회된다!!
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
