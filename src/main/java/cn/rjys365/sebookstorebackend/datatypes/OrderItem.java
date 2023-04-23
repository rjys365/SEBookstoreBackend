package cn.rjys365.sebookstorebackend.datatypes;

import cn.rjys365.sebookstorebackend.util.BookConst;

public class OrderItem {
    private Integer id;
    private Integer count;
    private Double price;
    private String title;
    public String getTitle(){
        return this.title;
    }
    public OrderItem(Integer id,Integer count){
        this.id = id;
        this.count = count;
        this.price = BookConst.books[id].getPrice();
        this.title = BookConst.books[id].getTitle();
    }
    public Integer getId(){
        return this.id;
    }
    public Integer getCount(){
        return this.count;
    }
    public Double getPrice(){
        return this.price;
    }
    public Double getSubTotal(){
        return this.price*this.count;
    }
    public String toString(){
        return "OrderItem "+this.id+", Count:"+this.count+", Price:"+this.price;
    }
//    public Book getBook(){
//        for (Book book : BookConst.books){
//            if (book.getId().equals(this.id)){
//                return book;
//            }
//        }
//        return null;
//    }
}
