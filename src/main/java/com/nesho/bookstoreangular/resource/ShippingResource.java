package com.nesho.bookstoreangular.resource;

import com.nesho.bookstoreangular.domain.User;
import com.nesho.bookstoreangular.domain.UserShipping;
import com.nesho.bookstoreangular.service.UserService;
import com.nesho.bookstoreangular.service.UserShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("shipping")
public class ShippingResource {
    @Autowired
    private UserService userService;
    @Autowired
    private UserShippingService userShippingService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity addShippingCardPost(
            @RequestBody UserShipping userShipping, Principal principal) {
        if (principal == null) {
            return new ResponseEntity("Payment not Added Some Error Happen", HttpStatus.BAD_REQUEST);
        }
        User user = userService.findByUsername(principal.getName());
        userService.updateUserShipping(userShipping, user);
        return new ResponseEntity("UserShipping Added(Updated) Successfull!", HttpStatus.OK);
    }

    @RequestMapping(value = "/getAll")
    public Set<UserShipping> getAllShippingList(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return user.getUserShippingList();
    }

    @RequestMapping(value = "/remove")
    public ResponseEntity removeUserShipping(@RequestBody String id) {
        Long lid = Long.valueOf(id);
        userShippingService.removeById(lid);
        return new ResponseEntity("UserShipping Removed Sucessfully!", HttpStatus.OK);
    }

    @RequestMapping(value = "/setDefault")
    public ResponseEntity setUserShippingDefault(@RequestBody String id, Principal principal) {
        Long lid = Long.valueOf(id);
        User user = userService.findByUsername(principal.getName());
        userService.setUserDefaultShipping(lid, user);
        return new ResponseEntity("UserShipping Default set Sucessfully!", HttpStatus.OK);
    }

}
