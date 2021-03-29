package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {
    /**
     * @return 할인 대상 금액
     */
    // 호출하고 나면, 얼마가 할인 됬는지 return 해줌.
    int discount(Member member, int price);
}
