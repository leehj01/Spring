package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

//public class OrderServiceImplBeforelombokTest {
//    // 수정자 주입으로 했을 경우, 의존관계가 없이 동작한다면 ?
//    @Test
//    void createOrder(){
//
//        OrderServiceImpl_beforelombok orderService = new OrderServiceImpl_beforelombok(new MemoryMemberRepository(), new FixDiscountPolicy());
//        orderService.createOrder(1L, "itemA", 10000);
//    }
//}
