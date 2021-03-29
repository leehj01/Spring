package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")

    // vip라면,
    void vip_o(){
        //given - 임의의 멤버를 만듦
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        //when
        int discount = discountPolicy.discount(member, 10000);

        //then - 1000 원이 나와야한다.
        assertThat(discount).isEqualTo(1000);
    }

    // vip 가 아닌 것도 나와야함 . - 실패테스트도 중요하다.
    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다. ")
    void vip_x(){
        //given - 임의의 멤버를 만듦
        Member member = new Member(2L, "memberBASIC", Grade.BASIC);

        //when
        int discount = discountPolicy.discount(member, 10000);

        //then - 1000 원이 나와야한다.
//        Assertions.assertThat(discount).isEqualTo(0);
        // Assertions - option + enter 눌러서 메서드를 줄여줌  그럼 위코드가 아래코드로 줄어듬
        assertThat(discount).isEqualTo(0);
    }

}