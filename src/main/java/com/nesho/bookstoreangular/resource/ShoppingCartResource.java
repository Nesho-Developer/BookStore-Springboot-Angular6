package com.nesho.bookstoreangular.resource;

import com.nesho.bookstoreangular.domain.Book;
import com.nesho.bookstoreangular.domain.CartItem;
import com.nesho.bookstoreangular.domain.ShoppingCart;
import com.nesho.bookstoreangular.domain.User;
import com.nesho.bookstoreangular.service.BookService;
import com.nesho.bookstoreangular.service.CartItemService;
import com.nesho.bookstoreangular.service.ShoppingCartService;
import com.nesho.bookstoreangular.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
public class ShoppingCartResource {
    @Autowired
    private BookService bookService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add")
    public ResponseEntity addItemToShoppingcart(
            @RequestBody Map<String, String> mapper, Principal principal) {
        Long bookId = Long.parseLong(mapper.get("bookId"));
        int qty = Integer.parseInt(mapper.get("qty"));
        User user = userService.findByUsername(principal.getName());
        Book book = bookService.findOne(bookId).get();
        System.out.println("mapper = [" + mapper + "], principal = [" + principal + "]");
        if (qty > book.getInStockNumber()) {
            return new ResponseEntity("notEnoughStouck!", HttpStatus.BAD_REQUEST);
        }
        CartItem cartItem = cartItemService.addBookToCartItem(book, user, qty);
        return new ResponseEntity("Book Added Successfully!", HttpStatus.OK);
    }

    @RequestMapping("/getCartItemList")
    public Set<CartItem> getCartItemList(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Set<CartItem> cartItems = cartItemService.findByShoppingCart(user.getShoppingCart());
        shoppingCartService.updateShoppingCart(user.getShoppingCart());
        return cartItems;
    }

    @RequestMapping("/getShoppingCart")
    public ShoppingCart getShoppingcart(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        ShoppingCart shoppingCart = user.getShoppingCart();
        shoppingCartService.updateShoppingCart(shoppingCart);
        return shoppingCart;
    }

    @RequestMapping("/removeItem")
    public ResponseEntity removeShoppingCart(@RequestBody String sid) {
        Long id = Long.parseLong(sid);
        cartItemService.removeCartItem(cartItemService.findById(id));
        return new ResponseEntity("CartItem Reoved sucessfully", HttpStatus.OK);
    }

    @RequestMapping("/updateCartItem")
    public ResponseEntity update(@RequestBody Map<String, String> mapper) {
        Long id = Long.parseLong(mapper.get("bookId"));
        int qty = Integer.parseInt(mapper.get("qty"));
        CartItem cartItem = cartItemService.findById(id);
        cartItem.setQty(qty);
        cartItemService.updateCartItem(cartItem);
        return new ResponseEntity("CardItem Updated successfully", HttpStatus.OK);


    }

}
