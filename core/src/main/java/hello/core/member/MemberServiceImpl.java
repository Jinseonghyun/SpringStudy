package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 구현 객체가 하나인 경우 관례상 인터페이스 뒤에 Impl 을 붙인다.
@Component
public class MemberServiceImpl implements MemberService{

    private MemberRepository memberRepository;

    @Autowired //의존 관계 자동 주입  = // ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
