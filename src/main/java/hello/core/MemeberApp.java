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

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
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
