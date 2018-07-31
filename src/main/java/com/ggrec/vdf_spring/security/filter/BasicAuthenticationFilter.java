package com.ggrec.vdf_spring.security.filter;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import sun.misc.BASE64Decoder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BasicAuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    }

//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest)req;
//        HttpServletResponse response = (HttpServletResponse)res;
//
//        String header = request.getHeader("Authorization");
//
//        if(header != null) {
//            if (header.startsWith("Basic ")) {
//                String[] usernamePassword = parseUsernamePassword(header.substring(6));
//
//                try {
//                    UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(usernamePassword[0], usernamePassword[1]);
//                    UsernamePasswordAuthenticationToken authResult = oneAccountAuthenticationManager.authenticate(authRequest);
//
//                    SecurityContextHolder.getContext().setAuthentication(authResult);
//                } catch(AuthenticationException e) {
//                    invalidAuthentication(request, response, "Authentication request failed for id: exception: ${e.toString()}", e)
//                    return
//                }
//            }
//        }
//        chain.doFilter(request, response);
//    }
//
//    private void invalidAuthentication(Object request, Object response, String msg, Exception e) {
//        SecurityContextHolder.getContext().setAuthentication(null);
//        authenticationEntryPoint.commence(request, response, e);
//    }
//
//    private String[] parseUsernamePassword(String encodedToken) throws IOException {
//        String token = new String(new BASE64Decoder().decodeBuffer(encodedToken), "UTF-8");
//        return token.split(":",2);
//    }

}
