package jpabook.jpashop.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Order;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class, id);
    }

    public List<Order> findAll(OrderSearch orderSearch){
        em.createQuery("select o from Order o join o.member m", Order.class);
    }

    public List<Order> findAllWithMemberDelivery() {
        return em.createQuery("select o from Order o" + " join fetch o.member m" +  " join fetch o.delivery d", Order.class).getResultList();
    }
}
