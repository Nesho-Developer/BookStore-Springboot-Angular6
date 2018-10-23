package com.nesho.bookstoreangular.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ShoppingCart implements Serializable {
    private static final Long serialVersionUID = 123556894785L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal GrandTotal;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shoppingCart", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<CartItem> cartItems = new HashSet<>();

    @OneToOne
    @JsonIgnore
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getGrandTotal() {
        return GrandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        GrandTotal = grandTotal;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
