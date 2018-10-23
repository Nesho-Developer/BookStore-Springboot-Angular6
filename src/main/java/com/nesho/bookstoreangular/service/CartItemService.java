package com.nesho.bookstoreangular.service;

import com.nesho.bookstoreangular.domain.Book;
import com.nesho.bookstoreangular.domain.CartItem;
import com.nesho.bookstoreangular.domain.ShoppingCart;
import com.nesho.bookstoreangular.domain.User;

import java.util.Set;

public interface CartItemService {
    CartItem addBookToCartItem(Book book, User user, int qty);

    Set<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

    //Set<CartItem> findByorder(Order order);
    CartItem updateCartItem(CartItem cartItem);

    void removeCartItem(CartItem cartItem);

    CartItem findById(Long id);

    CartItem save(CartItem cartItem);
}
