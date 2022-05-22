package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="orders")
@Getter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") //매핑을 뭘로 할건지 foreign key
    private Member member; //주문 회원

    private List<OrderItem> orderItems = new ArrayList<>();

    private Delivery delivery;
}
