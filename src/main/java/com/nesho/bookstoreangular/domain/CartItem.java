package com.nesho.bookstoreangular.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CartItem implements Serializable {
    private static final Long serialVersionUID = 1235568945L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal subTotal;
    private int qty;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @OneToMany(mappedBy = "cartItem")
    @JsonIgnore
    private Set<BookToCartItem> bookToCartItemList = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id")
    @JsonIgnore
    private ShoppingCart shoppingCart;
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "order_id")
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Set<BookToCartItem> getBookToCartItemList() {
        return bookToCartItemList;
    }

    public void setBookToCartItemList(Set<BookToCartItem> bookToCartItemList) {
        this.bookToCartItemList = bookToCartItemList;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
