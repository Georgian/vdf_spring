package com.ggrec.vdf_spring.security;

import com.ggrec.vdf_spring.repository.VDFAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

    @Autowired
    private VDFAccountRepository userRepository;

    @Override
    public String execute(Connection<?> connection) {
        System.out.println("signup === ");
//        final User user = new User();
//        user.setUsername(connection.getDisplayName());
//        user.setPassword(randomAlphabetic(8));
//        userRepository.save(user);
//        return user.getUsername();
        return "jorj";
    }

}