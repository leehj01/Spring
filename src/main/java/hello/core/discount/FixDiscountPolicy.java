package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy{

    // 우린 무조건 1000원 할인해주니깐, 이런식으로 작성!
    private int discountFixAmount = 1000; // 1000 원할인

    @Override
    public int discount(Member member, int price) {

        // 조건. 등급이 vip이면, 위의 해당.
        if (member.getGrade() == Grade.VIP){
            return discountFixAmount;
        }
        else{
            return 0;
        }

    }
}
