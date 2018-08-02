package com.ggrec.vdf_spring.controller;

import com.ggrec.vdf_spring.domain.VDFAccount;
import com.ggrec.vdf_spring.security.VDFUser;
import com.ggrec.vdf_spring.service.VDFAccountDetailsService;
import com.ggrec.vdf_spring.service.VDFAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@Controller
public class SignUpController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    private VDFAccountService vdfAccountService;

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String redirectRequestToRegistrationPage(WebRequest request, ModelMap modelMap) {
		Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
		UserProfile userProfile = connection.fetchUserProfile();

        VDFAccount userCreateRequestVO = VDFAccount.fromSocialUserProfile(userProfile);

        modelMap.put("user", userCreateRequestVO);

        return "Welcome, " + userCreateRequestVO.getEmail();
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String registrationUser(@ModelAttribute VDFAccount userCreateRequestVO, WebRequest request) throws Exception {
        try {
            VDFAccount newAccount = vdfAccountService.saveAccount(userCreateRequestVO);
            providerSignInUtils.doPostSignUp(newAccount.getEmail(), request);

            VDFUser frontUserDetail = VDFAccountDetailsService.vdfUser(newAccount);

            Authentication authentication = new UsernamePasswordAuthenticationToken(frontUserDetail, null, frontUserDetail.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return "redirect:/";
        } catch (Exception e) {
            return String.format("redirect:/error?message=%s", e.getMessage());
        }
    }

    /**
     * https://stackoverflow.com/a/40312144/1774643
     */
    @PostConstruct
    private void init() {
        try {
            String[] fieldsToMap = { "id", "email", "first_name" };

            Field field = Class.forName(
                    "org.springframework.social.facebook.api.UserOperations")
                    .getDeclaredField("PROFILE_FIELDS");
            field.setAccessible(true);

            Field modifiers = field.getClass().getDeclaredField("modifiers");
            modifiers.setAccessible(true);
            modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            field.set(null, fieldsToMap);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}