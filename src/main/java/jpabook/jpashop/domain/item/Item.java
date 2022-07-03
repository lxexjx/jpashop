package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughstockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
@Getter @Setter
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private  String name;
    private  int price;
    private int stockquantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비지니스 로직 : 제고에 관한==
    public void addStock(int quantity){
        this.stockquantity+=quantity;
    }
    public void removeStock(int quantity){
        int restStock = this.stockquantity-quantity;
        if(restStock<0){
            throw new NotEnoughstockException("nees more stock");
        }
        this.stockquantity = restStock;
    }

}
