package com.ggrec.vdf_spring.security;

import java.io.Serializable;

public final class UserPrincipalJSON implements Serializable {

    public String name;
    public String email;

    public UserPrincipalJSON(String name, String email) {
        this.name = name;
        this.email = email;
    }
}