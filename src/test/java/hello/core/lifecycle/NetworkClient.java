package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// 빈 생명주기 콜백 시작 - 가짜 네트워크 연결
public class NetworkClient {
    // InitializingBean 초기화 빈  - PropertiesSet( 의존관계 주입 ) 이 끝나면 호출해 주겠다!
    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출 = " + url);
        connect(); // 1. 객체를 생성하면서 바로 연결할거야!
        call("초기화 연결 메시지"); // 3. 다음 call
    }

    public void setUrl(String url){
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect(){
        System.out.println("connect = " + url); // 2 . 그러나 커넥트 하는데 url 값이 없다. 그래서 connect 가 null 이 됨
        // 즉 커넥트 실패
    }

    public void call(String message){ //3. 여기도 url 이 null 이라 메시지가 제대로 전송 못함 그래서 실패
        System.out.println("call " + url + " message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect(){
        System.out.println("close = " + url);
    }

    @PostConstruct
    public void init()  {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시");
    }

    @PreDestroy
    public void close() throws Exception {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
