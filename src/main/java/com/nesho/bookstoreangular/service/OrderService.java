package com.nesho.bookstoreangular.service;

import com.nesho.bookstoreangular.domain.*;

public interface OrderService {
    Order createOrder(ShoppingCart shoppingCart,
                      ShippingAddress shippingAddress,
                      BillingAddress billingAddress,
                      Payment payment,
                      String ShippingMethod,
                      User user);

    Order findOne(Long id);

}
