package com.nesho.bookstoreangular.service;

import com.nesho.bookstoreangular.domain.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);

    void clearShoppingCart(ShoppingCart shoppingCart);

}
