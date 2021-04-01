//package hello.core.order;
//
//import hello.core.discount.DiscountPolicy;
//import hello.core.discount.FixDiscountPolicy;
//import hello.core.discount.RateDiscountPolicy;
//import hello.core.member.Member;
//import hello.core.member.MemberRepository;
//import hello.core.member.MemoryMemberRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//// order service는 두개가 필요함
//@Component
//public class OrderServiceImpl_beforelombok implements OrderService{
//
//    // appconfig 전  ) 회원 찾기
//    // private final MemberRepository memberRepository = new MemoryMemberRepository();
//
//    // 1 ) 가격 정책이 필요함  - 변경전 ) 고정할인 정책
//    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//
//    // 2) 변경후 ) 가격 정책 - 정률정책 -> 위와 아래로 변경하면 문제점 ?!
//    // 할인정책을 변경하려면 클라이언트인 OrderServiceImpl (조금이지만) 코드를 바꿔야함. 그이유는, 인터페이스 뿐만아니라 구체클래스에도 의존하고 있기 때문!
//    // -> 추상(인터페이스)에만 의존하도록 변경해야한다!
//    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
//
//    // 3) 문제를 해결하는 방법 -> 추상(인터페이스)에만 의존하도록 변경해야한다! - dip를 지킴
//    // 이렇게 되면 인터페이스에만 의존하게 된다. 하지만, 구현체가 없기 때문에 null pointer exception 에러가 발생한다.
//    // -> 누군가 클라이언트 OrderServiceImpl에 DiscountPolicy의 구현객체를 대신 생성하고 주입해주어야 함 .
//
//    //setter 을 쓰려면 , final은 빼야함
//    private final MemberRepository memberRepository;
//    private final DiscountPolicy discountPolicy;
//
//////     setter 메서드 주입 코드
////    @Autowired
////    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
////        System.out.println("discountPolicy = " + discountPolicy);
////        this.discountPolicy = discountPolicy;
////    }
////
////    @Autowired(required = false)
////    public void setMemberRepository(MemberRepository memberRepository) {
////        System.out.println("memberRepository = " + memberRepository);
////        this.memberRepository = memberRepository;
////    }
//
//    // 생성자를 만듦 - 얘는 각각 파라미터가 무엇이 들어올지 알수 없당
//    @Autowired // 생성자위에 적어두면, 자동으로 아래 매개변수를 주입해줌
//    public OrderServiceImpl_beforelombok(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository; // 생성자에는 값을 넣어줘야 에러가 발생안함
//        this.discountPolicy = discountPolicy;
//    }
//
////    // 일반 메서드 주입 - 수정자 주입과 비슷 ..
////    @Autowired
////    public void init(MemberRepository memberRepository DiscountPolicy discountPolicy){
////        this.memberRepository = memberRepository;
////        this.discountPolicy = discountPolicy;
////    }
//
//    @Override
//    public Order createOrder(Long memberId, String itemName, int itemPrice) {
//
//        // 멤버를 찾음
//        Member member = memberRepository.findById(memberId);
//        // 할인을 하는데, 멤버를 넘김
//        int discountPrice = discountPolicy.discount(member, itemPrice);
//
//        return new Order(memberId, itemName, itemPrice, discountPrice);
//    }
//
//    // 테스트 용도
//    public MemberRepository getMemberRepository(){
//        return memberRepository;
//    }
//}
