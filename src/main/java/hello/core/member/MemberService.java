package hello.core.member;

// 2가지 기능을 함 - 회원 가입/ 회원 조회
public interface MemberService {

    // 회원 가입은 join
    void join(Member member);

    // 회원조회하는 것
    Member findMember(Long memberId);

}
