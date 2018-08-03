package com.ggrec.vdf_spring.security;

import com.ggrec.vdf_spring.service.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class VDFAuthenticationFilter extends GenericFilterBean {

    private TokenAuthenticationService tokenAuthenticationService;

    @Autowired
    public VDFAuthenticationFilter(TokenAuthenticationService tokenAuthenticationService) {
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

        setAuthenticationFromHeader((HttpServletRequest) request);

        chain.doFilter(request, response);
    }

    private void setAuthenticationFromHeader(HttpServletRequest request) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof VDFAccountAuthentication)) {
            final VDFAccountAuthentication userAuthentication = tokenAuthenticationService.getAuthentication(request);
            if (userAuthentication != null) {
                SecurityContextHolder.getContext().setAuthentication(userAuthentication);
            }
        }
    }
}