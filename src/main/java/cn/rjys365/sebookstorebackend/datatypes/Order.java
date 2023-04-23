package cn.rjys365.sebookstorebackend.datatypes;

import java.util.ArrayList;

public class Order {
    private ArrayList<OrderItem> items;
    private Integer id;
    public ArrayList<OrderItem> getItems(){
        return this.items;
    }
    public Integer getId(){
        return this.id;
    }
    public OrderDigest getDigest(){
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
        return new OrderDigest(this.id,itemCount,totalPrice,firstItemTitle);
    }
    public Order(Integer id,ArrayList<OrderItem> items){
        this.id = id;
        this.items = items;
    }
    public String toString(){
        String result = "Order "+this.id+":\n";
        for (OrderItem item : items){
            result += item.toString()+"\n";
        }
        return result;
    }
}
