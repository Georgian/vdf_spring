package com.ggrec.vdf_spring.security;

import com.ggrec.vdf_spring.domain.VDFAccount;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.UserIdSource;
import org.springframework.social.security.SocialAuthenticationToken;

public class UserAuthenticationUserIdSource implements UserIdSource {

    @Override
    public String getUserId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        VDFAccount user = null;
        if (authentication instanceof VDFAccount) {
            user = (VDFAccount) authentication.getPrincipal();
        }

        if (user == null) {
            throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
        }

        return user.getUserId();
    }
}