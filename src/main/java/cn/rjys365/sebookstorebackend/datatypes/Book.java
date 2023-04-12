package cn.rjys365.sebookstorebackend.datatypes;

public class Book {
    private Integer id;
    private String title;
    private String author;
    private String publisher;
    private Integer stock;
    private Double price;
    private String introduction;
    private String image;
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

    public Book(Integer id,String title,String author,String publisher,Integer stock,Double price,String image,String introduction){
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.stock = stock;
        this.price = price;
        this.image=image;
        this.introduction = introduction;
    }

}
