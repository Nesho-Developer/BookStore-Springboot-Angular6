package com.nesho.bookstoreangular.service;

import com.nesho.bookstoreangular.domain.UserPayment;

public interface UserPaymentService {
    UserPayment findById(Long id);

    void removeById(Long id);
}
