package com.nesho.bookstoreangular.domain.security;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

public class Authority implements GrantedAuthority, Serializable {
    private static final Long serialVersionUID = 123456l;
    private final String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
