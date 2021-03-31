package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@ComponentScan (// 스프링 빈을 쫙 끓어서 자동으로 스프링빈을 끌고 오는 것 -  자동으로 빈 등록
        basePackages = "hello.core.member",
        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type =  FilterType.ANNOTATION, classes = Configuration.class) // 자동으로 빈 등록하는데, 빼고 싶은 것 ( Configuration.cass 가 붙은 것을 뺴주겠다는 의미 / 기존예제 파일을 지키기 위해 )
)
@Configuration // 설정정보를 적어주는 애노테이션
public class AutoAppConfig {



}
