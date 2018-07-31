package com.ggrec.vdf_spring.security;

import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;

final public class VDFUser extends User {

    private int accountId;
    private String email;
    private String facebookId;

    public VDFUser(String email, String facebookId, String password) {
        super(email, password, true, true, true, true, new ArrayList<>());
        this.email = email;
        this.facebookId = facebookId;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getEmail() {
        return email;
    }

    public String getFacebookId() {
        return facebookId;
    }

}
