package hello.core.member;

public interface MemberRepository {

    void save(Member member); // 회원을 저장할 기능
    Member findById(Long memberId); // 회원 id 찾는 기능

}
