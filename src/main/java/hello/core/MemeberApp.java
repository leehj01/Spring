package hello.core;

import hello.core.member.*;

public class MemeberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        //회원가입
        memberService.join(member);

        // 찾은 멤버
        Member findMember = memberService.findMember(1L);
        System.out.println("new Member =" + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}
