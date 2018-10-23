package com.nesho.bookstoreangular.repository;

import com.nesho.bookstoreangular.domain.Order;
import com.nesho.bookstoreangular.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface OrderRepository extends CrudRepository<Order, Long> {
    Set<Order> findByUser(User user);
}
