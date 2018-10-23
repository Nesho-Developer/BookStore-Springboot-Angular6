package com.nesho.bookstoreangular.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nesho.bookstoreangular.domain.*;
import com.nesho.bookstoreangular.service.CartItemService;
import com.nesho.bookstoreangular.service.OrderService;
import com.nesho.bookstoreangular.service.ShoppingCartService;
import com.nesho.bookstoreangular.service.UserService;
import com.nesho.bookstoreangular.utility.MailConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
@RequestMapping("/checkOut")
public class CheckOutResource {
    private Order order = new Order();
    @Autowired
    private MailConstructor mailConstructor;
    @Autowired
    private MailSender mailSender;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartItemService cartItemService;

    @RequestMapping(value = "/checkOut", method = RequestMethod.POST)
    public Order checkOutOrder(@RequestBody Map<String, Object> mapper,
                               Principal principal) {
        System.out.println("mapper = [" + mapper + "], principal = [" + principal + "]");
        ObjectMapper objectMapper = new ObjectMapper();
        ShippingAddress shippingAddress = objectMapper.convertValue(mapper.get("shippingAddress"), ShippingAddress.class);
        BillingAddress billingAddress = objectMapper.convertValue(mapper.get("billingAddress"), BillingAddress.class);
        Payment payment = objectMapper.convertValue(mapper.get("payment"), Payment.class);
        String shippingMethod = (String) mapper.get("shippingMethod");
        System.out.println("shippingAddress" + shippingAddress.getId());
        System.out.println("billingAddress" + billingAddress.getId());
        System.out.println("payment" + payment.getId());
        System.out.println(shippingMethod);
        User user = userService.findByUsername(principal.getName());
        ShoppingCart shoppingCart = user.getShoppingCart();

        Order order = orderService.createOrder(shoppingCart, shippingAddress, billingAddress, payment, shippingMethod, user);
        javaMailSender.send(mailConstructor.constructOrderConformationMail(user, order, Locale.ENGLISH));
        shoppingCartService.clearShoppingCart(shoppingCart);
        LocalDate toDay = LocalDate.now();
        LocalDate estimatedDeliveryDate;
        if (shippingMethod.equalsIgnoreCase("groundShipping")) {
            estimatedDeliveryDate = toDay.plusDays(5);
        } else {
            estimatedDeliveryDate = toDay.plusDays(3);
        }
        this.order = order;
        return order;

    }
}
