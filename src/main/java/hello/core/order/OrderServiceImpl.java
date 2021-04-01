package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;


@Component
//@RequiredArgsConstructor // final 를 붙이면 필수값이 되기때문에, 이걸 가지고 생성자를 자동으로 만들어줌
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 생성자를 만듦 - 얘는 각각 파라미터가 무엇이 들어올지 알수 없당
    @Autowired // 생성자위에 적어두면, 자동으로 아래 매개변수를 주입해줌
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository; // 생성자에는 값을 넣어줘야 에러가 발생안함
        this.discountPolicy = discountPolicy;
    }

        @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        // 멤버를 찾음
        Member member = memberRepository.findById(memberId);
        // 할인을 하는데, 멤버를 넘김
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
    // 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
