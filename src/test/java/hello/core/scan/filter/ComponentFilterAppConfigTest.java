package hello.core.scan.filter;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComponentFilterAppConfigTest {
    @Test
    void filterScan(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        Assertions.assertThat(beanA).isNotNull();

//        ac.getBean("beanB", BeanB.class); // 배제 필터에 있는 bean이기 때문에 get하면 조회가 안되고, 에러가 발생한다.
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("beanB",BeanB.class));
    }

    @Configuration
    @ComponentScan( // 나만의 component를 스캔할수있는 기능이 만들어짐
            includeFilters = @ComponentScan.Filter(
                    type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(
                    type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
            )
    static class ComponentFilterAppConfig{

    }
}
