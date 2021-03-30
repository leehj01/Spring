package hello.core;
// 애플리케이션의 전체 동작방식을 구성하는 '기획자'를 만들어주자!
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//
// 리펙토링 전
//public class AppConfig {
//
//    // 먼저 MEMBER service를 여기서  만듦 . - " 생성자 주입 " 이라고 함
//    // appconfig는 생성한 객체 인스턴스의 참조를 생성자를 통해서 주입(연결)한다.
//    public MemberService memberService(){
//        return new MemberServiceImpl(new MemoryMemberRepository());
//    }
//
//    public OrderService orderService(){
//        return new OrderServiceImpl(
//                new MemoryMemberRepository(),
//                new FixDiscountPolicy()
//        );
//    }
//}

// 리펙토링 후
//public class AppConfig {
//    public MemberService memberService(){
//        return new MemberServiceImpl(memberRepository());
//    }
//
//    public OrderService orderService(){
//        return new OrderServiceImpl(
//                memberRepository(),
//                discountPolicy()
//        );
//    }
//
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }
//
//    public DiscountPolicy discountPolicy(){
////        return new FixDiscountPolicy();
//        return new RateDiscountPolicy();
//    }
//}

// 스프링 기반으로 변경 -> 이렇게 코를 넣으면 스프링 컨테이너에 넣어짐
@Configuration // 설정을 구성한 다는 뜻
public class AppConfig {

    @Bean // 스프링 컨테이너 빈으로 등록
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy()
        );
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
