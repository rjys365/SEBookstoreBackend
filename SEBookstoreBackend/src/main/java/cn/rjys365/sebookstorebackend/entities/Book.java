package cn.rjys365.sebookstorebackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name= "title")
    private String title;

    @Column(name="author")
    private String author;

    @Column(name="publisher")
    private String publisher;

    @Column(name="stock")
    private Integer stock;

    @Column(name="price")
    private Double price;

    @Column(name="introduction")
    private String introduction;

    @Column(name="image_url")//TODO:implement image upload
    private String image;

    @Transient
//    @JsonIgnore
    private BookInfo bookInfo;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CartItem> cartItems = new ArrayList<>();

    public Book(){}

    //    public Book(Integer id,String title,String author,String publisher,Integer stock,Double price,String image,String introduction){
//        this.id = id;
//        this.title = title;
//        this.author = author;
//        this.publisher = publisher;
//        this.stock = stock;
//        this.price = price;
//        this.image=image;
//        this.introduction = introduction;
//    }

}
