package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // 가입을 하고 회원을 찾으려면, 앞에서 만든 MemberRepository 가 필요함
    // 인터페이스를 적었으나, 구현체 없이 인터페이스만 가지면 null point 가 됨
    // 따라서 구현체(MemoryMemberRepository) 를 선택해줘야함.
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 조인에서 save를 호출하면, 다형성에 따라서 MemoryMemberRepository 가 호출됨
    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }


    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
