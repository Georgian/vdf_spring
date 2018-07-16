package com.ggrec.vdf_spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(path = "/api/account")
public class VDFAccountController {

    public Principal user(Principal principal) {
        return principal;
    }

}
