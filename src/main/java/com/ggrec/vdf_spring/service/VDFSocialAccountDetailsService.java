package com.ggrec.vdf_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

//@Service
public class VDFSocialAccountDetailsService implements SocialUserDetailsService {

    private VDFAccountDetailsService vdfAccountDetailsService;

    public VDFSocialAccountDetailsService(VDFAccountDetailsService vdfAccountDetailsService) {
        this.vdfAccountDetailsService = vdfAccountDetailsService;
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        return (SocialUserDetails) vdfAccountDetailsService.loadUserByFacebookId(userId);
    }

}
