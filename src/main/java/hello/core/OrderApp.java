package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// order 가 잘되는지 test하는 코드
public class OrderApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        // 멤버 아이디를 먼저 저장하기 위해서, 아래의 코드를 적음
        long memeberId = 1L;
        // vip를 만듦
        Member member = new Member(memeberId, "memberA", Grade.VIP);
        // 먼저,메모리에 정보를 넣음
        memberService.join(member);

        // 주문을 하는 코드
        Order order = orderService.createOrder(memeberId, "itemA", 10000);

        // to_string 으로 결과가 만들어짐.
        System.out.println("order = " + order);
    }
}

//order = Order{memberId=1, itemName='itemA', itemPrice=10000, discountPrice=1000} 결과가 나옴