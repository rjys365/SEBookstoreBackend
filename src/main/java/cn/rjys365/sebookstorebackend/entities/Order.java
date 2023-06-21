package cn.rjys365.sebookstorebackend.entities;

import cn.rjys365.sebookstorebackend.dto.OrderDigest;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="orders")
public class Order {

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "order")
    @JsonManagedReference
    private Set<OrderItem> items;

    @Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;

    @Column(name="user_id")
    private Integer userId;
    public Set<OrderItem> getItems(){
        return this.items;
    }

    public void setItems(Set<OrderItem> items){
        this.items = items;
    }

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public void setUserId(Integer userId){
        this.userId=userId;
    }

    public Integer getUserId(){
        return this.userId;
    }

//    public OrderDigest getDigest(){
//        Integer itemCount = 0;
//        Double totalPrice = 0.0;
//        String firstItemTitle = "";
//        for (OrderItem item : items){
//            itemCount += item.getCount();
//            totalPrice += item.getPrice()*item.getCount();
//            if (firstItemTitle.equals("")){
//                firstItemTitle = item.getTitle();
//            }
//        }
//        return new OrderDigest(this.id,itemCount,totalPrice,firstItemTitle);
//    }
//    public Order(Integer id,Set<OrderItem> items){
//        this.id = id;
//        this.items = items;
//    }
    public String toString(){
        StringBuilder result = new StringBuilder("Order " + this.id + ":\n");
        for (OrderItem item : items){
            result.append(item.toString()).append("\n");
        }
        return result.toString();
    }
}
