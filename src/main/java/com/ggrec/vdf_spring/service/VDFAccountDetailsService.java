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

    @Deprecated // Use load by email
    @Override
    public VDFUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return loadUserByEmail(username);
    }

    public VDFUser loadUserByFacebookId(String fid) throws UsernameNotFoundException {
        VDFAccount account = vdfAccountService.loadAccountByFacebookId(fid);
        return user(account);
    }

    public VDFUser loadUserByEmail(String email) throws UsernameNotFoundException {
        VDFAccount account = vdfAccountService.loadAccountByEmail(email);
        return user(account);
    }

    /**
     * Generates a spring UserDetails object from a VDF Account.
     * The details obj is custom and also contains social info
     */
    public VDFUser user(VDFAccount vdfAccount) {
        return new VDFUser(vdfAccount.getEmail(), vdfAccount.getFacebookId(), "passwordPlaceholder");
    }

}
