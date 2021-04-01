package hello.core.scan.filter;
// 애노테이션 관련은 어려우니.. 따로 공부해야한다고 함. ㅎ

import java.lang.annotation.*;

// 이아이가 붙은거는 component에 추가할거야 라는 의미 
@Target(ElementType.TYPE) // TYPE 은 클래스 래밸에 붙음
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
}
