package cn.rjys365.sebookstorebackend.dto;

import cn.rjys365.sebookstorebackend.entities.Order;
import cn.rjys365.sebookstorebackend.entities.OrderItem;

import java.util.Set;

public class OrderDigest {
    private Integer id;
    private Integer itemCount;
    private Double totalPrice;
    private String firstItemTitle;
    public Integer getId(){
        return this.id;
    }
    public Integer getItemCount(){
        return this.itemCount;
    }
    public Double getTotalPrice(){
        return this.totalPrice;
    }
    public String getFirstItemTitle(){
        return this.firstItemTitle;
    }
//    public OrderDigest(Integer id,Integer itemCount,Double totalPrice,String firstItemTitle){
//        this.id = id;
//        this.itemCount = itemCount;
//        this.totalPrice = totalPrice;
//        this.firstItemTitle = firstItemTitle;
//    }
    public OrderDigest(Order order){
        this.id = order.getId();
        Set<OrderItem> items = order.getItems();
        Integer itemCount = 0;
        Double totalPrice = 0.0;
        String firstItemTitle = "";
        for (OrderItem item : items){
            itemCount += item.getCount();
            totalPrice += item.getPrice()*item.getCount();
            if (firstItemTitle.equals("")){
                firstItemTitle = item.getTitle();
            }
        }
        this.itemCount=itemCount;
        this.totalPrice=totalPrice;
        this.firstItemTitle=firstItemTitle;
    }
}