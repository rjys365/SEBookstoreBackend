package cn.rjys365.sebookstorebackend.entities;

import cn.rjys365.sebookstorebackend.dto.OrderDigest;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name="orders")
public class Order {

    @Getter
    @OneToMany(mappedBy = "order")
    @JsonManagedReference
    private Set<OrderItem> items;

    @Getter
    @Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;

    @Getter
    @Column(name="user_id")
    private Integer userId;

    @Column(name="created_time")
    private LocalDateTime createdTime;

    public void setItems(Set<OrderItem> items){
        this.items = items;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public void setUserId(Integer userId){
        this.userId=userId;
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
