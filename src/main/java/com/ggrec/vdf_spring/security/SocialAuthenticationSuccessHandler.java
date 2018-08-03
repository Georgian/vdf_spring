package com.ggrec.vdf_spring.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggrec.vdf_spring.domain.VDFAccount;
import com.ggrec.vdf_spring.service.TokenAuthenticationService;
import com.ggrec.vdf_spring.service.VDFAccountSocialDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class SocialAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private TokenAuthenticationService tokenAuthenticationService;

    private VDFAccountSocialDetailsService accountSocialDetailsService;

    @Autowired
    public SocialAuthenticationSuccessHandler(
            TokenAuthenticationService tokenAuthenticationService,
            VDFAccountSocialDetailsService accountSocialDetailsService) {
        this.tokenAuthenticationService = tokenAuthenticationService;
        this.accountSocialDetailsService = accountSocialDetailsService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        // Lookup the complete User object from the database and create an Authentication for it
        final VDFAccount authenticatedUser = accountSocialDetailsService.loadUserByUsername(authentication.getName());

        // Add VDFAccountAuthentication to the response
        final VDFAccountAuthentication vdfAccountAuthentication = new VDFAccountAuthentication(authenticatedUser);
        tokenAuthenticationService.addAuthentication(response, vdfAccountAuthentication);

        super.onAuthenticationSuccess(request, response, authentication);
    }

}