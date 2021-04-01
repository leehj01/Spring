package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;
// 의존관계주입 - 옵션처리
public class AutowiredTest {

    @Test
    void AutowiredOtion(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean{

        // 매서드 자체가 호출 안됨
        @Autowired(required = false)
        public void setNobean1(Member nobean1){ // Member은  스프링 빈이 아님
            System.out.println("nobean1 = " + nobean1);
        }
        // null 호출
        @Autowired
        public void setNoBean2(@Nullable Member noBean2){
            System.out.println("noBean2 = " + noBean2); // noBean2 = null
        }
        // Optional.empty 호출
        @Autowired
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("noBean3 = " + noBean3); // noBean3 = Optional.empty
        }
    }
}

