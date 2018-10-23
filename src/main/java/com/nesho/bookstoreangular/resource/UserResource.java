package com.nesho.bookstoreangular.resource;

import com.nesho.bookstoreangular.config.SecurityUtility;
import com.nesho.bookstoreangular.domain.Role;
import com.nesho.bookstoreangular.domain.User;
import com.nesho.bookstoreangular.domain.UserRole;
import com.nesho.bookstoreangular.service.UserService;
import com.nesho.bookstoreangular.utility.MailConstructor;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
@RequestMapping("/user")
public class UserResource {
    @Autowired
    private UserService userService;
    @Autowired
    private MailConstructor mailConstructor;
    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public ResponseEntity newUserAccount(HttpServletRequest request,
                                         @RequestBody HashMap<String, String> mapper) {
        String username = mapper.get("username");
        String email = mapper.get("email");
        if (userService.findByUsername(username) != null) {
            return new ResponseEntity("UsernameExists", HttpStatus.BAD_REQUEST);
        }
        if (userService.findByEmail(email) != null) {
            return new ResponseEntity("EmailExists", HttpStatus.BAD_REQUEST);
        }
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        String password = SecurityUtility.randomPassword();
        newUser.setPassword(
                SecurityUtility.passwordEncoder().encode(password));
        Role role = new Role();
        role.setId(1);
        role.setName("ROLE_USER");
        Set<UserRole> roles = new HashSet<>();
        roles.add(new UserRole(newUser, role));
        newUser.setUserRoles(roles);
        System.out.println("request = [" + request + "], mapper = [" + newUser + "]");
        userService.createUser(newUser, roles);
        SimpleMailMessage semail = mailConstructor.constructNewUserMail(newUser, password);
        mailSender.send(semail);
        return new ResponseEntity("User Added Successfully", HttpStatus.OK);


    }

    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    public ResponseEntity forgetPassword(HttpServletRequest request,
                                         @RequestBody String email) {
        User user = userService.findByEmail(email);
        if (user == null) {
            return new ResponseEntity("Email not found.", HttpStatus.BAD_REQUEST);
        }

        String password = SecurityUtility.randomPassword();
        String ecyrpass = SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(ecyrpass);
        userService.save(user);
        SimpleMailMessage semail = mailConstructor.constructNewUserMail(user, password);
        mailSender.send(semail);
        return new ResponseEntity("Email sent!", HttpStatus.OK);

    }

    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    public ResponseEntity updateUserInfo(@RequestBody HashMap<String, Object> mapper) throws NotFoundException {

        System.out.println("mapper = [" + mapper + "]");
        Long id = Long.parseLong(String.valueOf(mapper.get("id")));
        String firstName = (String) mapper.get("firstName");
        String lastName = (String) mapper.get("lastName");
        String username = (String) mapper.get("username");
        String currentPassword = (String) mapper.get("currentPassword");
        String email = (String) mapper.get("email");
        String newPassword = (String) mapper.get("newPassword");
        System.out.println("mapper = [" + currentPassword + "]");

        Optional<User> user = userService.findById(id);
        if (user == null) {
            throw new NotFoundException("user not found");
        }
        User currentUser = user.get();
        if (userService.findByEmail(email) != null) {
            if (userService.findByEmail(email).getId() != currentUser.getId()) {
                return new ResponseEntity("Email not belong to this Account!", HttpStatus.BAD_REQUEST);

            }
        }
        if (userService.findByEmail(email) == null && email != null && email.trim() != "") {
            currentUser.setEmail(email);
        }

        if (userService.findByUsername(username) != null) {
            if (userService.findByUsername(username).getId() != currentUser.getId()) {
                return new ResponseEntity("Username not found!", HttpStatus.BAD_REQUEST);
            }
        }
        System.out.println("mapper = [" + currentPassword + "]");
        if (newPassword != null && !newPassword.isEmpty() && newPassword != "") {
            boolean matchPass = SecurityUtility.passwordEncoder().matches(currentPassword, currentUser.getPassword());
            if (matchPass) {
                System.out.println("mapper = [" + currentPassword + "]");
                System.out.println("mapper = [" + currentUser.getPassword() + "]");
                currentUser.setPassword(SecurityUtility.passwordEncoder().encode(newPassword));
            } else {
                System.out.println("mapper = [" + currentPassword + "]" + matchPass);
                System.out.println("mapper = [" + currentUser.getPassword() + "]");
                return new ResponseEntity("Incorrect current Password", HttpStatus.BAD_REQUEST);
            }
        }
        currentUser.setFirstName(firstName);
        currentUser.setLastName(lastName);
        currentUser.setUsername(username);
        userService.save(currentUser);
        return new ResponseEntity(currentUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/getCurrentUser", method = RequestMethod.GET)
    public User getCurrentUser(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return user;
    }

}
