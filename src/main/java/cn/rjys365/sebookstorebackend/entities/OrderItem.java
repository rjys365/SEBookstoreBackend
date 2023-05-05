package cn.rjys365.sebookstorebackend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="order_items")
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Integer primaryKey;

    @Column(name="book_id")
    private Integer id;//book id

    @Column(name="quantity")
    private Integer count;

    @Column(name="price")
    private Double price;//price for each book

    @Column(name="title")
    private String title;

    @ManyToOne
    @JoinColumn(name="order_id")
    @JsonBackReference
    private Order order;

    public Order getOrder(){
        return this.order;
    }

    public void setOrder(Order order){
        this.order = order;
    }

    public String getTitle(){
        return this.title;
    }

//    public OrderItem(Integer id,Integer count){
//        this.id = id;
//        this.count = count;
//        this.price = 0.0;//TODO
//        this.title = "TODO";//TODO
//    }
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
    public void setBook(Book book){
        this.id = book.getId();
        this.price = book.getPrice();
        this.title = book.getTitle();
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
