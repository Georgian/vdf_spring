package com.ggrec.vdf_spring.security;

import com.ggrec.vdf_spring.domain.VDFAccount;
import com.ggrec.vdf_spring.service.VDFAccountDetailsService;
import com.ggrec.vdf_spring.service.VDFAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class FacebookAuthenticationManager implements AuthenticationManager {

    private VDFAccountService vdfAccountService;

    private VDFAccountDetailsService vdfAccountDetailsService;

    private FacebookService facebookService;

    @Autowired
    public FacebookAuthenticationManager(VDFAccountService vdfAccountService, VDFAccountDetailsService vdfAccountDetailsService, FacebookService facebookService) {
        this.vdfAccountService = vdfAccountService;
        this.vdfAccountDetailsService = vdfAccountDetailsService;
        this.facebookService = facebookService;
    }

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {

        VDFUser fbUser = facebookService.findUser((String) auth.getCredentials());
        if (fbUser != null && fbUser.getFacebookId().equals(auth.getPrincipal())) {

            VDFUser user = vdfAccountDetailsService.loadUserByFacebookId(fbUser.getFacebookId());

            if (user == null) {
                user = vdfAccountDetailsService.loadUserByEmail(fbUser.getEmail());

                if (user == null) {
                    VDFAccount newAccount = new VDFAccount();
                    newAccount.setFacebookId(fbUser.getFacebookId());
                    newAccount.setEmail(fbUser.getEmail());
                    newAccount = vdfAccountService.saveAccount(newAccount);
                    user = vdfAccountDetailsService.user(newAccount);

                } else if (user.getFacebookId() == null) {
                    VDFAccount account = vdfAccountService.loadAccountByEmail(fbUser.getEmail());
                    account.setFacebookId(fbUser.getFacebookId());
                    account = vdfAccountService.saveAccount(account);
                    user = vdfAccountDetailsService.user(account);
                }
            }

            if (user != null)
                return new UsernamePasswordAuthenticationToken(user, auth.getCredentials(), user.getAuthorities());
        }

        throw new BadCredentialsException("Authentication Failed");
    }

}
