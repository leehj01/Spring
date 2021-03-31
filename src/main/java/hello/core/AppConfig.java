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
    // @Bean memberService -> new MemoryMemberRepository()
    //  @Bean OrderService -> new MemoryMemberRepository() 이렇게 되면, 싱글톤이 깨지는것처럼 보이는데 괜찮은가 ?

    @Bean // 스프링 컨테이너 빈으로 등록
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService"); // soutm 하면 자동으로 완성! - 호출로그를 남김 - 왜냐면, 호출이 안되서 중복 주소값을 가지는지 궁금해서!
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy()
        );
    }

    @Bean
    public MemberRepository memberRepository(){
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
// 출력이 3번 등등 이러나야 하는데, 결국 1번만 일어난다.  -> 그 이유는 Configuration  떄문이다.!
//call AppConfig.memberRepository
//        call AppConfig.orderService