package com.ggrec.vdf_spring.service;

import com.ggrec.vdf_spring.domain.VDFAccount;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface VDFAccountSocialDetailsService extends SocialUserDetailsService, UserDetailsService {

    VDFAccount loadAccountByConnectionKey(ConnectionKey connectionKey);

    VDFAccount loadAccountByUserId(Long userId);

    VDFAccount loadAccountByEmail(String email);

    VDFAccount saveAccount(VDFAccount user);


    @Override
    default VDFAccount loadUserByUserId(String userId) throws UsernameNotFoundException {
        return loadAccountByUserId(Long.parseLong(userId));
    }

    @Override
    default VDFAccount loadUserByUsername(String username) throws UsernameNotFoundException {
        return loadAccountByEmail(username);
    }

}
