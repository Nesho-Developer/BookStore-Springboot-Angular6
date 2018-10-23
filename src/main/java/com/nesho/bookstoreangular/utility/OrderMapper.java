package com.nesho.bookstoreangular.utility;

import com.nesho.bookstoreangular.domain.BillingAddress;
import com.nesho.bookstoreangular.domain.Payment;
import com.nesho.bookstoreangular.domain.ShippingAddress;

public class OrderMapper {
    private ShippingAddress shippingAddress;
    private BillingAddress billingAddress;
    private Payment payment;
    private String shippingMethod;

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

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }
}
