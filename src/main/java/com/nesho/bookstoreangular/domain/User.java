package com.nesho.bookstoreangular.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nesho.bookstoreangular.domain.security.Authority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User implements UserDetails, Serializable {
    private static final Long serialVersionUID = 234156l;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    Set<UserRole> userRoles = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    Set<UserPayment> userPayments = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    Set<UserShipping> userShippingList = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    Set<Order> orderList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    ShoppingCart shoppingCart;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "Id", nullable = false, updatable = false)
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private boolean enabled = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public Set<UserPayment> getUserPayments() {
        return userPayments;
    }

    public void setUserPayments(Set<UserPayment> userPayments) {
        this.userPayments = userPayments;
    }

    public Set<UserShipping> getUserShippingList() {
        return userShippingList;
    }

    public void setUserShippingList(Set<UserShipping> userShippingList) {
        this.userShippingList = userShippingList;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Set<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(Set<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        userRoles.forEach(userRole -> authorities.add(new Authority(userRole.getRole().getName())));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
