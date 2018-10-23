package com.nesho.bookstoreangular.utility;

import com.nesho.bookstoreangular.domain.Order;
import com.nesho.bookstoreangular.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.util.Locale;


@Component
public class MailConstructor {
    @Autowired
    Environment env;
    @Autowired
    private TemplateEngine templateEngine;

    public SimpleMailMessage constructNewUserMail(User user, String passsword) {
        String message = "\n please use the following Credentials to log and edit your personal " +
                "information including your own  Password "
                + "\n Username :" + user.getUsername() + "\n Password :" + passsword;
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("le`s BookStore - New User");
        email.setFrom(env.getProperty("email.support"));
        email.setText(message);
        return email;
    }

    public MimeMessagePreparator constructOrderConformationMail(User user, Order order, Locale english) {
        Context context = new Context();
        context.setVariable("order", order);
        context.setVariable("user", user);
        context.setVariable("cartItemList", order.getCartItemList());
        String text = templateEngine.process("orderConformationMailTemplate", context);
        MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
                email.setTo("nesho.a.ali@gmail.com");
                email.setSubject("Le's Bookstore-order");
                email.setText(text, true);
                email.setFrom("a.ali.necho@hotmail.com");
            }
        };
        return mimeMessagePreparator;
    }
}
