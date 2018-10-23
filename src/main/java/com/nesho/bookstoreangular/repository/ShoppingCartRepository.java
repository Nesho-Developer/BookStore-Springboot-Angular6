package com.nesho.bookstoreangular.repository;

import com.nesho.bookstoreangular.domain.ShoppingCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {
}
