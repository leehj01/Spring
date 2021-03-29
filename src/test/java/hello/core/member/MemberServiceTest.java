package hello.core.member;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join(){
        //given  ~ 이 주어졌을때,
        Member member = new Member(1L, "memberA", Grade.VIP );

        //when ~ 을 했을 때, - 멤버를 찾아서
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then ~ 한다.
        Assertions.assertThat(member).isEqualTo(findMember);

    }
}
