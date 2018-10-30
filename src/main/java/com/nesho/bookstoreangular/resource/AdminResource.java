package com.nesho.bookstoreangular.resource;

import com.nesho.bookstoreangular.domain.User;
import com.nesho.bookstoreangular.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminResource {
    @Autowired
    private UserService userService;

    @RequestMapping("/users")
    public Iterable<User> getAll() {
        return userService.findAll();
    }

}
