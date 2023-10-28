package cn.rjys365.sebookstorebackend.dto;

import cn.rjys365.sebookstorebackend.entities.Book;
import cn.rjys365.sebookstorebackend.entities.CartItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemResponse {
    private Integer id;//bookId
    private Integer cartItemId;
    private String title;
    private String image;
    private Integer quantity;
    private Double price;
    private Double totalPrice;

    public CartItemResponse(CartItem cartItem) {
        Book book = cartItem.getBook();
        this.id = book.getId();
        this.cartItemId = cartItem.getId();
        this.title = book.getTitle();
        this.image = book.getImage();
        this.quantity = cartItem.getQuantity();
        this.price = book.getPrice();
        this.totalPrice = this.price * this.quantity;
    }
}
