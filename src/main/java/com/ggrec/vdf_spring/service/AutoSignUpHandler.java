package com.ggrec.vdf_spring.service;

import com.ggrec.vdf_spring.domain.VDFAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutoSignUpHandler implements ConnectionSignUp {

    @Autowired
    private VDFAccountDetailsService accountDetailsService;

    public AutoSignUpHandler(VDFAccountDetailsService accountDetailsService) {
        this.accountDetailsService = accountDetailsService;
    }

    @Override
    @Transactional
    public String execute(Connection<?> connection) {
        // Add new users to the db with its default roles
        // for later use in SocialAuthenticationSuccessHandler
        VDFAccount newAccount = accountDetailsService.saveAccount( VDFAccount.fromSocialConnection(connection) );
        return newAccount.getUserId();
    }

}