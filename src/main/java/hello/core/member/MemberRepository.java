package hello.core.member;

public interface MemberRepository {
    // 회원을 저장하는 것
    void save(Member member);

    // 회원의 아이디로 회원을 찾는 것
    Member findById(Long memberId);
}
