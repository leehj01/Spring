package hello.core.member;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

// 회원 저장소 만드기
// 데이터 베이스에서 아직 확정이 안됬기 때문에 이것을 만듦
@Component // 빈이름은 : memoryMemberRepository
public class MemoryMemberRepository implements MemberRepository {

    // 파이썬의 딕셔너
    private static Map<Long, Member> store = new HashMap<>();


    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    // memgerid 를 찾는것
    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
