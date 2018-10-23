package com.nesho.bookstoreangular.repository;

import com.nesho.bookstoreangular.domain.BookToCartItem;
import com.nesho.bookstoreangular.domain.CartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookToCartItemRepository extends CrudRepository<BookToCartItem, Long> {
    void deleteByCartItem(CartItem cartItem);
}
