package com.nesho.bookstoreangular.service.impl;

import com.nesho.bookstoreangular.domain.*;
import com.nesho.bookstoreangular.repository.BookToCartItemRepository;
import com.nesho.bookstoreangular.repository.CartItemRepository;
import com.nesho.bookstoreangular.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;


@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private BookToCartItemRepository bookToCartItemRepository;

    @Override
    public CartItem addBookToCartItem(Book book, User user, int qty) {
        Set<CartItem> cartItems = findByShoppingCart(user.getShoppingCart());

        for (CartItem cartItem : cartItems) {
            if (cartItem.getBook().getId() == book.getId()) {
                cartItem.setQty(cartItem.getQty() + qty);
                cartItem.setSubTotal(new BigDecimal(book.getOurPrice()).multiply(new BigDecimal(qty)));
                cartItemRepository.save(cartItem);
                return cartItem;

            }
        }
        CartItem cartItem = new CartItem();
        cartItem.setQty(qty);
        cartItem.setSubTotal(new BigDecimal(book.getOurPrice()).multiply(new BigDecimal(qty)));
        cartItem.setBook(book);
        cartItem.setShoppingCart(user.getShoppingCart());
        cartItem = cartItemRepository.save(cartItem);
        BookToCartItem bookToCartItem = new BookToCartItem();
        bookToCartItem.setCartItem(cartItem);
        bookToCartItem.setBook(book);
        bookToCartItemRepository.save(bookToCartItem);
        return cartItem;
    }

    @Override
    public Set<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
        return cartItemRepository.findByShoppingCart(shoppingCart);
    }

    @Override
    public CartItem updateCartItem(CartItem cartItem) {
        CartItem dbCartItem = cartItemRepository.findById(cartItem.getId()).get();
        if (dbCartItem == null) {
            return null;
        }
        BigDecimal bigDecimal = new BigDecimal(cartItem.getBook().getOurPrice()).multiply(new BigDecimal(cartItem.getQty()));
        bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        cartItem.setSubTotal(bigDecimal);
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void removeCartItem(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
        bookToCartItemRepository.deleteByCartItem(cartItem);

    }

    @Override
    public CartItem findById(Long id) {
        return cartItemRepository.findById(id).get();
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);

    }

}
