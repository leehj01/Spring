package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// 스프링 빈 조회 - 상속 관계
public class ApplicationContextExtendFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상이면 중복 오류가 발생한다.")
    void findBeanByParentTypeDuplicate(){
        // 아래를 하면, DiscountPolicy(부모 타입)으로 조회면 자식이 2개이기 때문에 둘다 가져옴 -> 따라서 에러가 발생함 -> 에러처리 코드 작성!
//        DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class)); // 람다식이라고한다 . assertThrows - 예외일때 통과해라.  Throws - 예외를 만든다.?
        // 에러가 발생하면 뒤의 인자의 에러와 앞의 에러가 같은지 체크함.
    }


    // 그러면, 빈 이름을 지정하면 됨!
    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈 이름으로 지정하면 됨")
    void findBeanByParentTypeBeanName(){
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanBySubType(){
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);

    }

    // ac.getBeansOfType : 해당 타입의 모든 빈을 조회할 수 있다.
    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByParentType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        for (String key : beansOfType.keySet()){
            System.out.println("key = " + key + "value = " + beansOfType.get(key));
            // 실제 현업엔 test case에는 프런트를 하지 않는다. 그냥 test통과 하냐 안하냐 를 파악하면 됨.
            //key = rateDiscountPolicyvalue = hello.core.discount.RateDiscountPolicy@77147f9e
            //key = fixDiscountPolicyvalue = hello.core.discount.FixDiscountPolicy@52475845
        }
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - object" )
    void findAllBeanObjectType(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()){
            System.out.println("key  = " + key + "value = " + beansOfType.get(key));
        } // 위의 결과 스프링의 내부 빈까지 다 딸려서 나옴. - 왜냐하면 모든것은 자바 객체는 모두 object type이라서
    }

    // 역할과 구현을 나누기 위해서 아래처럼 할인 정책 코드를 나뉘게 한것이다.
   @Configuration
    static class TestConfig{
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }


}
