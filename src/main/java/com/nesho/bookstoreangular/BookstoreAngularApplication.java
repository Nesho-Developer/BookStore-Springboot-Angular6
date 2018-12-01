package com.nesho.bookstoreangular;

import com.nesho.bookstoreangular.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookstoreAngularApplication implements CommandLineRunner {

    @Autowired
    private UserServiceImpl userService;

    public static void main(String[] args) {
        SpringApplication.run(BookstoreAngularApplication.class, args);
    }

    @Override
    public void run(String... args) {
//		User user=new User();
//		user.setUsername("nesho");
//		user.setEmail("a@g.com");
//		user.setEnabled(true);
//		user.setPassword(SecurityUtility.passwordEncoder().encode("nesho"));
//		user.setPhone("0111255488");
//		Set<UserRole> userRoles=new HashSet<>();
//		Role role =new Role();
//
//		role.setName("User_Role");
//		userRoles.add(new UserRole(user,role));
//		userService.createUser(user,userRoles);
//		userRoles.clear();
    }
}
