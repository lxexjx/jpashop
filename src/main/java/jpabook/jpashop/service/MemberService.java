package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;

    //회원 가입
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member){
       List<Member> findMembers = memberRepository.findByName(member.getName());

       if(!findMembers.isEmpty()){
           throw new IllegalStateException("이미 존재하는 회원입니다.");
       }
    }

    //회원 전체 조회
    @Transactional(readOnly = true)
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Member findMembers(Long memberId){
        return memberRepository.findOne(memberId);
    }

    @Transactional(readOnly = true)
    public void update(Long id,String name){
        Member member = memberRepository.findOne(id);
        member.setName(name);

    }

}
