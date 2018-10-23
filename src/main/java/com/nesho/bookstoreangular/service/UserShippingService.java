package com.nesho.bookstoreangular.service;

import com.nesho.bookstoreangular.domain.UserShipping;

public interface UserShippingService {
    UserShipping findById(Long id);

    void removeById(Long id);

}
