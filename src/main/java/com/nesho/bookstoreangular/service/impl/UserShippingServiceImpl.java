package com.nesho.bookstoreangular.service.impl;

import com.nesho.bookstoreangular.domain.UserShipping;
import com.nesho.bookstoreangular.repository.UserShippingRepository;
import com.nesho.bookstoreangular.service.UserShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserShippingServiceImpl implements UserShippingService {
    @Autowired
    private UserShippingRepository userShippingRepository;

    @Override
    public UserShipping findById(Long id) {
        return userShippingRepository.findById(id).get();
    }

    @Override
    public void removeById(Long id) {
        userShippingRepository.deleteById(id);

    }
}
