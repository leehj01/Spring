package hello.core.singletion;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SingletonService {
    //1. static 영역에 객체를 딱 1개만 생성한다
    private static final SingletonService instance = new SingletonService();

    //2. 조회 : public 으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
    // 이 객체 인스턴스가 필요하면 오직 getInstance 메서드를 통해서만 조회가능!  - 호춯하면 항상 같은 인스턴스 반환
    public static SingletonService getInstance() {
        return instance;
    }

    // 3. 생성자를 private으로 선언해서 외부해서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    // 딱 1개의 객체 인스턴스만 존재해야하므로, private으로 외부에서 객체 생성 막기
    private SingletonService() {

    }


    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }




}