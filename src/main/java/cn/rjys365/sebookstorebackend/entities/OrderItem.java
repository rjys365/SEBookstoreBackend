package cn.rjys365.sebookstorebackend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="order_items")
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;

    @Column(name="book_id")
    private Integer bookId;//book id

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

    public Double getSubTotal(){
        return this.price*this.count;
    }
    public String toString(){
        return "OrderItem "+this.bookId +", Count:"+this.count+", Price:"+this.price;
    }
    public void setBookIdPriceTitle(Book book){
        this.bookId = book.getId();
        this.price = book.getPrice();
        this.title = book.getTitle();
    }

}
