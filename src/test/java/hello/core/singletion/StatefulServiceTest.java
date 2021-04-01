package hello.core.singletion;

import hello.core.beanfind.ApplicationContextExtendFindTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;
// 상태를 유지할 경우 발생하는 문제점
class StatefulServiceTest {
    @Test
    void statefulServiceSingleton(){

        ApplicationContext ac  = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // ThreadA : A 사용자 10000 원 주문
//        statefulService1.order("userA", 10000);
        // -> 무상태를 위한 코드
        int userAPrice = statefulService1.order("userA", 10000);

        // ThreadB : B 사용자 10000 원 주문
//        statefulService2.order("userB", 20000);
        // -> 무상태를 위한 코드
        int userBPrice = statefulService1.order("userA", 10000);

        // ThreadA : 사용자 A 주문 금액 조회
//        int price = statefulService1.getPrice();
        // ThreadA : 사용자 A는 10000원을 긷했지만, 기대와 다르게 20000원 출력
//        System.out.println("price = " + price); // price = 20000 - 그 이유는 statefulService1 와 statefulService2 는 같기 때문에 ..

        System.out.println("userAPrice = " + userAPrice); // userAPrice = 10000 으로 문제가 해결됨.
//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }
    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService() ;
        }
    }
}