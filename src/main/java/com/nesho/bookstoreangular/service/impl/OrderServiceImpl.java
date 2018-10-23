package com.nesho.bookstoreangular.service.impl;

import com.nesho.bookstoreangular.domain.*;
import com.nesho.bookstoreangular.repository.BillingAddressRepository;
import com.nesho.bookstoreangular.repository.OrderRepository;
import com.nesho.bookstoreangular.repository.PaymentRepository;
import com.nesho.bookstoreangular.repository.ShippingAddressRepository;
import com.nesho.bookstoreangular.service.CartItemService;
import com.nesho.bookstoreangular.service.OrderService;
import com.nesho.bookstoreangular.utility.MailConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ShippingAddressRepository shippingAddressRepository;
    @Autowired
    private BillingAddressRepository billingAddressRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private MailConstructor mailConstructor;

    @Override
    public synchronized Order createOrder(ShoppingCart shoppingCart,
                                          ShippingAddress shippingAddress,
                                          BillingAddress billingAddress,
                                          Payment payment,
                                          String ShippingMethod,
                                          User user) {

        Order order = new Order();
        order.setBillingAddress(billingAddress);
        System.out.println("shippingAddress" + shippingAddress.toString());
        order.setShippingAddress(shippingAddress);
        order.setShippingMethod(ShippingMethod);
        order.setUser(user);
        order.setPayment(payment);
        order.setOrderStatus("created");
        Set<CartItem> cartItems = cartItemService.findByShoppingCart(shoppingCart);
        for (CartItem cartItem : cartItems) {
            Book book = cartItem.getBook();
            book.setInStockNumber(book.getInStockNumber() - cartItem.getQty());
            cartItem.setOrder(order);
        }
        payment.setOrder(order);
        billingAddress.setOrder(order);
        shippingAddress.setOrder(order);
        order.setCartItemList(cartItems);
        order.setOrderDate(Calendar.getInstance().getTime());
        order.setOrderTotal(shoppingCart.getGrandTotal());
        orderRepository.save(order);
        return order;

    }

    @Override
    public Order findOne(Long id) {
        return orderRepository.findById(id).get();
    }

}
