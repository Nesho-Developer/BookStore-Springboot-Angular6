package com.nesho.bookstoreangular.resource;

import com.nesho.bookstoreangular.domain.User;
import com.nesho.bookstoreangular.domain.UserBilling;
import com.nesho.bookstoreangular.domain.UserPayment;
import com.nesho.bookstoreangular.service.UserPaymentService;
import com.nesho.bookstoreangular.service.UserService;
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
@RequestMapping("/payment")
public class PaymentResource {
    @Autowired
    private UserService userService;
    @Autowired
    private UserPaymentService userpaymentService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity addNewCreditCardPost(
            @RequestBody UserPayment userPayment, Principal principal) {
        if (principal == null) {
            return new ResponseEntity("Payment not Added Some Error Happen", HttpStatus.BAD_REQUEST);
        }
        User user = userService.findByUsername(principal.getName());

        UserBilling userBilling = userPayment.getUserBilling();
        userService.updateUserBilling(userBilling, userPayment, user);

        return new ResponseEntity("Payment Added(Updated) Successfully", HttpStatus.OK);


    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ResponseEntity removePaymentPost(
            @RequestBody String id, Principal principal) {
        //User user=userService.findByUsername(principal.getName());
        Long k = Long.parseLong(String.valueOf(id));
        System.out.println("id = [" + id + "], principal = [" + principal + "]");
        UserPayment userPayment = userpaymentService.findById(k);
        System.out.println("id = [" + userPayment + "]");
        userpaymentService.removeById(k);
        return new ResponseEntity("Payment Removed Successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/setDefault", method = RequestMethod.POST)
    public ResponseEntity setDefaultPaymentPost(
            @RequestBody String id, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        userService.setUserDefaultPayment(Long.parseLong(String.valueOf(id)), user);
        return new ResponseEntity("Default Payment change  Successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/getUserPayments", method = RequestMethod.GET)
    public Set<UserPayment> getUserPayments(Principal principal) {
        User user = userService.findByUsername(principal.getName());

        Set<UserPayment> userPayments = user.getUserPayments();
        return userPayments;
    }

}
