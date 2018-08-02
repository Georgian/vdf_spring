package com.ggrec.vdf_spring.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.social.security.SocialUserDetails;

import java.util.ArrayList;

public class VDFUser extends User implements SocialUserDetails {

    private String facebookId;

    public VDFUser(String email, String facebookId) {
        super(email, "passwordPlaceholder", new ArrayList<>());
    }

    @Override
    public String getUserId() {
        return facebookId;
    }
}
