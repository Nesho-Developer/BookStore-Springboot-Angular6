package com.nesho.bookstoreangular.service;

import com.nesho.bookstoreangular.domain.User;
import com.nesho.bookstoreangular.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserSecurityService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserSecurityService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        System.out.println("username = [" + user.getPassword() + "]");

        if (user != null) {

            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(user.getPassword());
            builder.roles(user.getUserRoles().toString());

        } else {
            log.warn("user {} not found", username);
            throw new UsernameNotFoundException("username " + username + " not found");
        }

        return builder.build();
    }
}
