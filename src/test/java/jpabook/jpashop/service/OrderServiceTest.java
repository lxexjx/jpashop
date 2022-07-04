package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughstockException;
import jpabook.jpashop.repository.OrderRepository;
import org.apache.tomcat.jni.Address;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Order;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception{
        //given
        Member member = createMember();

        Book book = createBook("시골 JPA",10000,10);

        int orderCount = 2;
        //when
        orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder=orderRepository.findOne(orderId);
        assertEquals("상품 준시 상태는 ORDER", OrderStatus.Order,getOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다", 1,getOrder.getOrderItems().size());
        assertEquals("주문 가격은 가격* 수량이다.",10000*orderCount, getOrder.getTotalprice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다.",8,book.getStockquantity());
    }

    @Test(expected = NotEnoughstockException.class)
    public void 상품주문_재고수량초과() throws Exception{
        //given
        Member member = createMember();
        Item item = createBook("시골 JPA",10000,10);

        int orderCount = 11;

        //when
        orderService.order(member.getId(), item.getId(), orderCount);

        //then
        fail("재고 수량 부족 예외가 발생해야 한다.");

    }
    
    public void 주문취소() throws Exception{
        //given
        Member member = createMember();
        Book item = createBook("시골 JPA",10000,10);

        int orderCount = 2;

        Long orderID = orderService.Order(member.getId(), item.getId(), orderCount);

        //when
        orderService.cancelOrder(orderID);

        //then
        Order getOrder = orderRepository.findOne(orderID);

        assertEquals("주문 취소시 상태는 CANCEL이다.",OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals("주문 취소된 상품은 그만큼 재고가 증가해야 한다.", 10, item.getStockquantity());

    }

    private Book createBook(String name,int price,int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockquantity(stockQuantity);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("ej1");
        member.setAddress(new Address("서울","강가","123-123"));
        em.persist() member;
        return member;
    }

}
