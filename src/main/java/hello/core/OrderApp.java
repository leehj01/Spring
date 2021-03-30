package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// order 가 잘되는지 test하는 코드
public class OrderApp {

    public static void main(String[] args) {
        // config 전
        // MemberService memberService = new MemberServiceImpl();
        // OrderService orderService = new OrderServiceImpl();

        // spring 전
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        long memeberId = 1L; // 멤버 아이디를 먼저 저장하기 위해서
        Member member = new Member(memeberId, "memberA", Grade.VIP); // vip를 만듦
        memberService.join(member);  // 먼저,메모리에 정보를 넣음

        // 주문을 하는 코드
        Order order = orderService.createOrder(memeberId, "itemA", 10000);

        // to_string 으로 결과가 만들어짐.
        System.out.println("order = " + order);
    }
}

//order = Order{memberId=1, itemName='itemA', itemPrice=10000, discountPrice=1000} 결과가 나옴