package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


@RunWith(SpringRunner.class)    //junit이라 스프링이랑 엮어서 같이 실행할래!
@SpringBootTest //컨테이너안에서 실행
@Transactional  //롤백하기 위한
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    //@Rollback(false)
    public void 회원가입() throws Exception{

        Member member =new Member();
        member.setName("ej");

        Long savedId = memberService.join(member);

        em.flush();
        Assertions.assertEquals(member,memberRepository.findOne(savedId));
    }

    @Test //(expected=IllegalStateException)
    public void 중복회원예외() throws Exception{
        Member member1 = new Member();
        member1.setName("ej");

        Member member2 = new Member();
        member2.setName("ej");

        memberService.join(member1);
        try{
            memberService.join(member2);
        }catch (IllegalStateException e){
            return;
        }
       // fail("에외가 발생해야 합니다.");
    }
}