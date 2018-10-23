package com.nesho.bookstoreangular.repository;

import com.nesho.bookstoreangular.domain.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
