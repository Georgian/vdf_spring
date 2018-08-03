package com.ggrec.vdf_spring.controller;

import com.ggrec.vdf_spring.domain.VDFAccount;
import com.ggrec.vdf_spring.domain.VDFAccountRole;
import com.ggrec.vdf_spring.security.VDFAccountAuthentication;
import com.ggrec.vdf_spring.service.VDFAccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "/account")
public class VDFAccountController {

    @Autowired
    private VDFAccountDetailsService accountDetailsService;

    @RequestMapping(value = "/user/current", method = RequestMethod.GET)
    public VDFAccount getCurrent() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof VDFAccountAuthentication) {
            return ((VDFAccountAuthentication) authentication).getDetails();
        }
        return new VDFAccount(); //anonymous user support
    }

    @RequestMapping(value = "/admin/user/{user}/grant/role/{role}", method = RequestMethod.POST)
    public ResponseEntity<String> grantRole(@PathVariable VDFAccount user, @PathVariable VDFAccountRole role) {
        if (user == null) {
            return new ResponseEntity<>("invalid user id", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        user.grantRole(role);
        accountDetailsService.saveAccount(user);
        return new ResponseEntity<>("role granted", HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/user/{user}/revoke/role/{role}", method = RequestMethod.POST)
    public ResponseEntity<String> revokeRole(@PathVariable VDFAccount user, @PathVariable VDFAccountRole role) {
        if (user == null) {
            return new ResponseEntity<>("invalid user id", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        user.revokeRole(role);
        accountDetailsService.saveAccount(user);
        return new ResponseEntity<>("role revoked", HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/user", method = RequestMethod.GET)
    public List<VDFAccount> list() {
        return accountDetailsService.findAll();
    }

}
