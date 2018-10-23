package com.nesho.bookstoreangular.service.impl;

import com.nesho.bookstoreangular.domain.CartItem;
import com.nesho.bookstoreangular.domain.ShoppingCart;
import com.nesho.bookstoreangular.repository.ShoppingCartRepository;
import com.nesho.bookstoreangular.service.CartItemService;
import com.nesho.bookstoreangular.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CartItemService cartItemService;


    @Override
    public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) {
        BigDecimal totalCart = new BigDecimal(0);
        Set<CartItem> cartItems = cartItemService.findByShoppingCart(shoppingCart);
        for (CartItem cartItem : cartItems) {
            if (cartItem.getBook().getInStockNumber() > 0) {
                cartItemService.updateCartItem(cartItem);
                totalCart = totalCart.add(cartItem.getSubTotal());
                shoppingCart.setGrandTotal(totalCart);
            }
        }

        shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }

    @Override
    public void clearShoppingCart(ShoppingCart shoppingCart) {
        cartItemService.findByShoppingCart(shoppingCart).forEach(cartItem -> {
            cartItem.setShoppingCart(null);
            cartItemService.updateCartItem(cartItem);
        });
        shoppingCart.setGrandTotal(new BigDecimal(0));
        shoppingCart.getCartItems().clear();
        shoppingCartRepository.save(shoppingCart);

    }
}
