package com.nesho.bookstoreangular.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_order")
public class Order implements Serializable {
    private static final Long serialVersionUID = 1254857568l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date orderDate;
    private Date shippingDate;
    private String shippingMethod;
    private String orderStatus;
    private BigDecimal orderTotal;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<CartItem> cartItemList = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    private ShippingAddress shippingAddress;

    @OneToOne(cascade = CascadeType.ALL)
    private BillingAddress billingAddress;

    @OneToOne(cascade = CascadeType.ALL)
    private Payment payment;

    @ManyToOne
    @JsonIgnore
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Set<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(Set<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
