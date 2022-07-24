package jpabook.jpashop.repository.order;

import jpabook.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataJPAMemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByName(String name);
}
