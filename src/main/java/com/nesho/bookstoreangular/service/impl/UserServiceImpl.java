package com.nesho.bookstoreangular.service.impl;

import com.nesho.bookstoreangular.config.SecurityUtility;
import com.nesho.bookstoreangular.domain.*;
import com.nesho.bookstoreangular.repository.*;
import com.nesho.bookstoreangular.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserPaymentRepository userPaymentRepository;
    @Autowired
    private UserBillingRepository userBillingRepository;
    @Autowired
    private UserShippingRepository userShippingRepository;

    @Override
    public User createUser(User user, Set<UserRole> userRole) {
        User user1 = userRepository.findByUsername(user.getUsername());
        if (user1 != null) {
            LOG.warn("user" + user.getFirstName() + " is already exist.");
        } else {
            for (UserRole urole : userRole) {
                roleRepository.save(urole.getRole());
            }
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUser(user);
            user.setShoppingCart(shoppingCart);
            user.getUserRoles().addAll(userRole);
            user.setUserPayments(new HashSet<UserPayment>());
            user.setUserShippingList(new HashSet<UserShipping>());
            user1 = userRepository.save(user);
        }
        return user1;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public String findByUsernameAndPassword(String username, String passsword) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return "username not exist";
        }
        boolean encyrpass = SecurityUtility.passwordEncoder().matches(passsword, user.getPassword());
        System.out.println((encyrpass) + " username = [" + username + "], passsword = [" + passsword + "]\n" + user.getPassword() + "\n" + SecurityUtility.passwordEncoder().encode(passsword) + encyrpass);

        if (encyrpass) {
            return "user exist";
        }
        return "password incorrect";

    }


    @Override
    public void updateUserPaymentInfo(UserBilling userBilling, UserPayment userPayment, User user) {
        save(user);
        userPaymentRepository.save(userPayment);
        userBillingRepository.save(userBilling);

    }

    @Override
    public void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user) {
        userPayment.setUser(user);
        userPayment.setUserBilling(userBilling);
        userPayment.setDefaultPayment(true);
        userBilling.setUserPayment(userPayment);
        user.getUserPayments().add(userPayment);
        save(user);
    }

    @Override
    public void setUserDefaultPayment(Long paymentId, User user) {
        List<UserPayment> userPayments = (List<UserPayment>) userPaymentRepository.findAll();
        for (UserPayment userPayment : userPayments) {
            if (userPayment.getId() == paymentId) {
                userPayment.setDefaultPayment(true);
                userPaymentRepository.save(userPayment);
            } else {
                userPayment.setDefaultPayment(false);
                userPaymentRepository.save(userPayment);
            }
        }

    }

    @Override
    public void updateUserShipping(UserShipping userShipping, User user) {
        userShipping.setUser(user);
        userShipping.setUserShippingDefault(true);
        user.getUserShippingList().add(userShipping);
        save(user);
    }

    @Override
    public void setUserDefaultShipping(Long shippingId, User user) {
        List<UserShipping> userShippings = (List<UserShipping>) userShippingRepository.findAll();
        for (UserShipping userShipping : userShippings) {
            if (userShipping.getId() == shippingId) {
                userShipping.setUserShippingDefault(true);
                userShippingRepository.save(userShipping);
            } else {
                userShipping.setUserShippingDefault(false);
                userShippingRepository.save(userShipping);
            }
        }
    }
}
