package com.ggrec.vdf_spring.security;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import org.springframework.stereotype.Service;

@Service
public class FacebookService {

    public VDFUser findUser(String token) {
        try {
            FacebookClient facebookClient = new DefaultFacebookClient(token, Version.VERSION_2_9);
            com.restfb.types.User user = facebookClient.fetchObject(
                    "me",
                    com.restfb.types.User.class,
                    Parameter.with("fields","id,first_name,email"));

            if (user == null) {
                Thread.sleep(500);
                user = facebookClient.fetchObject(
                        "me",
                        com.restfb.types.User.class,
                        Parameter.with("fields","id,first_name,email"));
            }

            if (user != null)
                return myCarfaxUser(user);

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    private VDFUser myCarfaxUser(com.restfb.types.User facebookUser) {
        return new VDFUser(
                facebookUser.getEmail(),
                facebookUser.getId(),
                "passwordPlaceholder");
    }

}
