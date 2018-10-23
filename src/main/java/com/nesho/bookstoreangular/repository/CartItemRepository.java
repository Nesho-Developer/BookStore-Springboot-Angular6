package com.nesho.bookstoreangular.repository;

import com.nesho.bookstoreangular.domain.CartItem;
import com.nesho.bookstoreangular.domain.ShoppingCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Long> {
    Set<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
    //Set<CartItem> findByorder(Order order);
}
