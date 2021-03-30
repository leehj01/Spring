package hello.core.singletion;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너 - 스프링 없는 아이와 아래의 스프링 있는 애 비교 ")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        //1. 조회 : 호출할때마다 객체를 생성하는지 ? 조회
        MemberService memberService1 = appConfig.memberService();

        //2. 조회
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인 - 즉, 객체가 계속 생성되서 올라가게 됨.
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        // memberService1 = hello.core.member.MemberServiceImpl@70afa564
        // memberService2 = hello.core.member.MemberServiceImpl@50e8dcf1

        // 자동화 - 참조값이 다른 것을 확인
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        // private 으로 생성자를 막아두어 컴파일 오류 발생된다.
//        new SingletonService();

        // 1.조회 : 호출할 때마다 같은 객체를 반환
        SingletonService singletonService1 = SingletonService.getInstance();
        // 2. 조회
        SingletonService SingletonService2 = SingletonService.getInstance();

        // 참조값이 같은 것을 확인 - 같은 주소를 공유한다.
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("SingletonService2 = " + SingletonService2);
        //singletonService1 = hello.core.singletion.SingletonService@11c6bf5d
        //SingletonService2 = hello.core.singletion.SingletonService@11c6bf5d

        // test로
        assertThat(singletonService1).isSameAs(SingletonService2);

        singletonService1.logic();
    }

    // 스프링 컨테이너를 사용하는 테스트 코드
    @Test
    @DisplayName("스프링 컨테이너와 싱글톤 ") // 이전에는 pure 컨테이너라고 함.
    void springContainer(){
        //        AppConfig appConfig = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);


        //1. 조회 : 호출할때마다 객체를 생성하는지 ? 조회
//        MemberService memberService1 = appConfig.memberService();
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        //2. 조회
//        MemberService memberService2 = appConfig.memberService();
        MemberService memberService2  = ac.getBean("memberService", MemberService.class);

        // 참조값이 다른 것을 확인 - 즉, 객체가 계속 생성되서 올라가게 됨.
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // 자동화 - 참조값이 같은것 확인
        Assertions.assertThat(memberService1).isSameAs(memberService2);
        //memberService1 = hello.core.member.MemberServiceImpl@16d7cd48
        //memberService2 = hello.core.member.MemberServiceImpl@16d7cd48
    }


}
