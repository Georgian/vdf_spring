package com.ggrec.vdf_spring.service;

import com.ggrec.vdf_spring.domain.VDFAccount;
import com.ggrec.vdf_spring.security.VDFUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class VDFAccountDetailsService implements UserDetailsService {

    private VDFAccountService vdfAccountService;

    @Autowired
    public VDFAccountDetailsService(VDFAccountService vdfAccountService) {
        this.vdfAccountService = vdfAccountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return vdfUser( vdfAccountService.loadAccountByEmail(username) );
    }

    public UserDetails loadUserByFacebookId(String facebookId) {
        return vdfUser( vdfAccountService.loadAccountByFacebookId(facebookId) );
    }

    public static VDFUser vdfUser(VDFAccount vdfAccount) {
        return vdfAccount == null ? null : new VDFUser(vdfAccount.getFacebookId(), vdfAccount.getEmail());
    }

}
