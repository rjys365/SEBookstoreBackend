package cn.rjys365.sebookstoresearchservice.entity;

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

}
