package com.nesho.bookstoreangular.service;

import com.nesho.bookstoreangular.domain.*;

import java.util.Optional;
import java.util.Set;

public interface UserService {
    User createUser(User user, Set<UserRole> userRole);

    User findByUsername(String username);

    User findByEmail(String email);

    Optional<User> findById(Long id);

    User save(User user);

    String findByUsernameAndPassword(String username, String passsword);

    void updateUserPaymentInfo(UserBilling userBilling, UserPayment userPayment, User user);

    void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user);

    void setUserDefaultPayment(Long PaymentId, User user);

    void updateUserShipping(UserShipping userShipping, User user);

    void setUserDefaultShipping(Long ShippingId, User user);
}
