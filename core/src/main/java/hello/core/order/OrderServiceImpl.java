package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor  // final 이 붙은 필드를 모아서 생성자를 자동으로 만들어준다
public class OrderServiceImpl implements OrderService {

//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // DIP 에 위반 되지 않게 추상화에만 의존하게 바꾸자
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    private DiscountPolicy rateDiscountPolicy;

    @Autowired // 생성자 위에 다가 @Autowired 스프링이 생성할 때 의존관계 주입  (생정자를 통한 의존관계 주입 (불변))
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // 주문생성 요청이 오면 회원정보를 조회하고 할인을 한다.
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    ///테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
