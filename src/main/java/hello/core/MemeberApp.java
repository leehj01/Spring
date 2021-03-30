package hello.core;

import hello.core.member.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemeberApp {
    public static void main(String[] args) {
        // config 전
        // MemberService memberService = new MemberServiceImpl();

        // spring 쓰기 전 ,
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        // ApplicationContext : 스프링 컨테이너 - 다 담아주는 아이
        // appconfig에 있는 환경설정 가지고, @bean 붙어있는것들을 관리해줌,
        // 직접 찾아오는 것이 아니라, 스프링 컨테이너에 담아서 가져오게 되는것
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // 이름은 이거고, 뒤에는 타입을 불러온다는 의미.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);


         Member member = new Member(1L, "memberA", Grade.VIP);
        //회원가입
        memberService.join(member);

        // 찾은 멤버
        Member findMember = memberService.findMember(1L);
        System.out.println("new Member =" + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}
