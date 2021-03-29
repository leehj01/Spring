package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

// order service는 두개가 필요함
public class OrderServiceImpl implements OrderService{

    // 회원 찾기
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 가격 정책이 필요함  - 변경전 ) 고정할인 정책
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    // 변경후 ) 가격 정책 - 정률정책 -> 위와 아래로 변경하면 문제점 ?!
    // 할인정책을 변경하려면 클라이언트인 orderserviceimpl 코드를 바꿔야함.
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        // 멤버를 찾음
        Member member = memberRepository.findById(memberId);
        // 할인을 하는데, 멤버를 넘김
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
