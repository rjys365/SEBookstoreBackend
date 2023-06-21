package cn.rjys365.sebookstorebackend.controller;

import cn.rjys365.sebookstorebackend.dto.CartItemRequest;
import cn.rjys365.sebookstorebackend.dto.CartItemResponse;
import cn.rjys365.sebookstorebackend.dto.OrderDetailsDTO;
import cn.rjys365.sebookstorebackend.entities.Book;
import cn.rjys365.sebookstorebackend.entities.CartItem;
import cn.rjys365.sebookstorebackend.entities.Order;
import cn.rjys365.sebookstorebackend.service.BookService;
import cn.rjys365.sebookstorebackend.service.OrderService;
import cn.rjys365.sebookstorebackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carts/")
@CrossOrigin("http://localhost:3000")
public class CartController {
    private final UserService userService;

    private final BookService bookService;

    private final OrderService orderService;

    public CartController(UserService userService, BookService bookService, OrderService orderService) {
        this.userService = userService;
        this.bookService = bookService;
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public List<CartItemResponse> getCartItems(@PathVariable Integer id) {
        Optional<List<CartItem>> cartItemsOptional = userService.getUserCartItems(id);
        if (cartItemsOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Illegal get cart operation");
        return cartItemsOptional.get().stream().map(CartItemResponse::new).toList();
    }

    @PutMapping("/{id}")
    public List<CartItemResponse> setCartItems(@PathVariable Integer id, @RequestBody List<CartItemRequest> cartItemsDTO) {
        List<CartItem> cartItems = new ArrayList<>();
        for (CartItemRequest itemRequest : cartItemsDTO) {
            Optional<Book> bookOptional = bookService.getBookById(itemRequest.getId());
            if (bookOptional.isEmpty())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Illegal set cart operation");
            Book book = bookOptional.get();
            CartItem cartItem = new CartItem();
            cartItem.setBook(book);
            if (itemRequest.getQuantity() == null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Illegal set cart operation");
            cartItem.setQuantity(itemRequest.getQuantity());
            cartItems.add(cartItem);
        }
        Optional<List<CartItem>> cartItemsOptional = userService.setUserCartItems(id, cartItems);
        if (cartItemsOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Illegal set cart operation");
        return cartItemsOptional.get().stream().map(CartItemResponse::new).toList();
    }

    @PatchMapping("/{id}")
    public List<CartItemResponse> updateCartItem(@PathVariable Integer id, @RequestBody CartItemRequest cartItemRequest,
                                                 @RequestParam(required = false) String operation) {
        Optional<List<CartItem>> cartItemsOptional;
        if (operation != null && operation.equals("add"))
            cartItemsOptional = userService.addUserCartItem(id, cartItemRequest.getId(), cartItemRequest.getQuantity());
        else
            cartItemsOptional = userService.setUserCartItem(id, cartItemRequest.getId(), cartItemRequest.getQuantity());
        if (cartItemsOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Illegal set cart operation");
        return cartItemsOptional.get().stream().map(CartItemResponse::new).toList();
    }
}
