package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // 가입을 하고 회원을 찾으려면, 앞에서 만든 MemberRepository 가 필요함
    // 인터페이스를 적었으나, 구현체 없이 인터페이스만 가지면 null point 가 됨
    // 따라서 구현체(MemoryMemberRepository) 를 선택해줘야함.

    // 1) appconfig 만들기 전 - 이건 왼쪽(추상화)에도 의존하고 오른쪽(할당하는 부분)또한 구현체를 의존함 -> 변경이 있을 때, 문제가 됨 = dip를 위반
    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 2) appconfig 만든 후, 객체 지향에 가까운 코드
    private final MemberRepository memberRepository;

    // 생성자 만들기 - 생성자를 통해서, memberRepository 구현체가 뭐가 들어갈지 여기서 결정 ( 인터페이스만 존재 )
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 조인에서 save를 호출하면, 다형성에 따라서 MemoryMemberRepository 가호출됨
    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }


    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
