package cn.rjys365.sebookstorebackend.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
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

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CartItem> cartItems = new ArrayList<>();
    public Integer getId(){
        return this.id;
    }
    public String getTitle(){
        return this.title;
    }
    public String getAuthor(){
        return this.author;
    }

    public String getPublisher(){
        return this.publisher;
    }

    public Integer getStock(){
        return this.stock;
    }

    public Double getPrice(){
        return this.price;
    }

    public String getIntroduction(){
        return this.introduction;
    }

    public String getImage(){return this.image;}

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
