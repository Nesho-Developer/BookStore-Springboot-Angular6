package com.nesho.bookstoreangular.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role implements Serializable {

    private static  final Long serialversionUID = 213456l;
    @Id
    @Column(name = "role_id",nullable = false,updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roleId;
    private String name;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "role")
    Set<UserRole> userRole = new HashSet<>();

    public Role() {
    }

    public int getId() {
        return roleId;
    }

    public void setId(int id) {
        this.roleId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    public Set<UserRole> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }
}

