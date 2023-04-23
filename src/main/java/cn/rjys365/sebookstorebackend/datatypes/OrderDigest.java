package cn.rjys365.sebookstorebackend.datatypes;

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
    public OrderDigest(Integer id,Integer itemCount,Double totalPrice,String firstItemTitle){
        this.id = id;
        this.itemCount = itemCount;
        this.totalPrice = totalPrice;
        this.firstItemTitle = firstItemTitle;
    }
}
