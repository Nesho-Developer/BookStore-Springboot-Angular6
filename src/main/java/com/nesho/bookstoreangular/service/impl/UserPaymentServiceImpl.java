package com.nesho.bookstoreangular.service.impl;

import com.nesho.bookstoreangular.domain.UserPayment;
import com.nesho.bookstoreangular.repository.UserPaymentRepository;
import com.nesho.bookstoreangular.service.UserPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPaymentServiceImpl implements UserPaymentService {
    @Autowired
    private UserPaymentRepository userPaymentRepository;

    @Override
    public UserPayment findById(Long id) {
        return userPaymentRepository.findById(id).get();
    }

    @Override
    public void removeById(Long id) {
        userPaymentRepository.deleteById(id);
    }
}
