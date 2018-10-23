package com.nesho.bookstoreangular.resource;

import com.nesho.bookstoreangular.domain.Order;
import com.nesho.bookstoreangular.domain.User;
import com.nesho.bookstoreangular.service.OrderService;
import com.nesho.bookstoreangular.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/order")
public class OrderResource {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @RequestMapping("/getOrderList")
    public Set<Order> getAllOrders(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return user.getOrderList();
    }
}
