package com.library.auth.entity;

import javax.persistence.*;

@Entity
public class Role {

    public static final String ADMIN = "ADMIN";
    public static final String EMPLOYEE = "EMPLOYEE";
    public static final String STUDENT = "STUDENT";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Role() {}

    public Role(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
