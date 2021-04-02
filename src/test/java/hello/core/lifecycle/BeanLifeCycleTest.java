package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest(){
        //ConfigurableApplicationContext 가 부모이기 때문에 오른쪽을 담을 수 있다.
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient cli = ac.getBean(NetworkClient.class);
        ac.close();
        // ApplicationContext 는 close 할일이 별로 없기 때문에, 제공하지 않는다 . 따라서 하위로 내려가야함

    }


    @Configuration
    static class LifeCycleConfig{
        // 스프링 빈이 생성이 되고, 호출된 결과물이 스프링빈에 등록이 되고 이름은 networkClient 임
        @Bean
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient(); // 1. 객체를생성한 다음에
            networkClient.setUrl("http://hello-spring.dev"); // 주소는 가짜다 2. 필요한 값을 setter로 넣어줌 - 설정이 들어옴
            return networkClient;

        }
    }


}
